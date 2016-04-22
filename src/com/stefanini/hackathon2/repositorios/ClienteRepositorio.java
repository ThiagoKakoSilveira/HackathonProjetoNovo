package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.stefanini.hackathon2.entidades.Cliente;

import sun.rmi.log.ReliableLog;


@SuppressWarnings("all")
public class ClienteRepositorio {
	
	@Inject
	private EntityManager entityManager;
	
	public void inserir(Cliente cliente) {
		entityManager.persist(cliente);
	}
	
	public List<Cliente> todasPessoas(){
		return entityManager.createQuery("select p from " + Cliente.class.getSimpleName() + " p").getResultList();
	}
	
	public void remover(Cliente cliente){
		entityManager.remove(entityManager.merge(cliente));
	}
	
	public void removerPorId(Integer id){
		Cliente entidade = entityManager.find(Cliente.class, id);
		entityManager.remove(entidade);
	}
	
	public void atualizar(Cliente cliente){
		entityManager.merge(cliente);
	}
	
	public Cliente pesquisarPorId(Integer id){
		return entityManager.find(Cliente.class, id);
	}
	
	public Cliente pesquisarPorCpf(String cpf){
		String sql = "select p from "+ Cliente.class.getSimpleName() + " p where p.cpf = '"+ cpf +"'";
		List resultList = entityManager.createQuery(sql).getResultList();
		return ((resultList != null && !resultList.isEmpty() )? ((Cliente) resultList.get(0)) : null);
	}

	public boolean existePessoaComCpf(String cpf) {
		String sql = "select count(1) from " + Cliente.class.getSimpleName() + " p where p.cpf = '" + cpf + "'";
		long quantidade = (long) entityManager.createQuery(sql).getSingleResult();
		return (quantidade > 0);
	}
	
//	@PrePersist
//	@PreUpdate
//	public void antesDeSalvar(Cliente cliente) {
//		cliente.setCpf(cliente.getCpf().replace(".", ""));
//	}
//	
//	@PostLoad
//	public void depoisDeCarregar(Cliente cliente) {
//		cliente.setCpf("999.999.999-99");
//	}
}
