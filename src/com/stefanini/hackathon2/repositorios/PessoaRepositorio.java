package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;


import com.stefanini.hackathon2.entidades.Pessoa;


@SuppressWarnings("all")
public class PessoaRepositorio {
	
	@Inject
	private EntityManager entityManager;
	
	public void inserir(Pessoa pessoa) {
		entityManager.persist(pessoa);
	}
	
	public List<Pessoa> todasPessoas(){
		return entityManager.createQuery("select p from " + Pessoa.class.getSimpleName() + " p").getResultList();
	}
	
	public void remover(Pessoa pessoa){
		entityManager.remove(entityManager.merge(pessoa));
	}
	
	public void removerPorId(Integer id){
		Pessoa entidade = entityManager.find(Pessoa.class, id);
		entityManager.remove(entidade);
	}
	
	public void atualizar(Pessoa pessoa){
		entityManager.merge(pessoa);
	}
	
	public Pessoa pesquisarPorId(Integer id){
		return entityManager.find(Pessoa.class, id);
	}
	
	public Pessoa pesquisarPorCpf(String cpf){
		return entityManager.find(Pessoa.class, cpf);
	}

	public boolean existePessoaComCpf(String cpf) {
		String sql = "select count(1) from " + Pessoa.class.getSimpleName() + " p where p.cpf = '" + cpf + "'";
		int quantidade = (int) entityManager.createQuery(sql).getSingleResult();
		return (quantidade > 0);
	}

}
