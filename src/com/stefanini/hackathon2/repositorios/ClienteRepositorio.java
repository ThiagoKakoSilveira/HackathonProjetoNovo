package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;


import com.stefanini.hackathon2.entidades.Cliente;


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
		return (Cliente) entityManager.createQuery(sql).getSingleResult();
	}

	public boolean existePessoaComCpf(String cpf) {
		String sql = "select count(1) from " + Cliente.class.getSimpleName() + " p where p.cpf = '" + cpf + "'";
		long quantidade = (long) entityManager.createQuery(sql).getSingleResult();
		return (quantidade > 0);
	}
}
