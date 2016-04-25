package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.hackathon2.entidades.Funcionario;
import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.util.Mensageiro;
@SuppressWarnings("all")
public class LoginRepositorio {
	
	@Inject
	private EntityManager entityManager;
	
	public void inserir(Login login){
		entityManager.persist(login);		
	}
	
	public List<Login>todosLogin(){
		return entityManager.createQuery("select l from "+Login.class.getSimpleName()+" l").getResultList();
	}
	
	public void remover (Login login){
		entityManager.remove(login);
	}
	
	public void removerPorId(Integer id){
		Login login = entityManager.find(Login.class, id);
		entityManager.remove(login);
	}
	
	public void atualizar(Login login){		
		entityManager.merge(login);		
	}
	
	public boolean existeAcessoNobanco(Login login){
		int acessos = entityManager.createNativeQuery("select * from login where acesso ="+login.getAcesso()).getFirstResult();
		return (acessos > 0);
	}
	
	public Login pesquisarPorId(Integer id){
		return entityManager.find(Login.class, id);
	}
		
}
