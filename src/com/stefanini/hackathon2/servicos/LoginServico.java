package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.repositorios.LoginRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;
import com.stefanini.hackathon2.util.Mensageiro;



public class LoginServico {
	
	@Inject
	private LoginRepositorio repositorio;
	
	@Transacional
	public void salvar(Login login){
		if(login.getId()==null){
			if(repositorio.existeAcessoNobanco(login)){
				Mensageiro.nootificaErro("Erro!!", "Login já existe");
			}else
			repositorio.inserir(login);
		}else{
			repositorio.atualizar(login);
		}
		
	}
	
	@Transacional
	public void deletar(Login login){
		repositorio.remover(login);
	}
	
	@Transacional
	public List<Login>carregarTodosLoginsDoBanco(){
		return repositorio.todosLogin();
	}

}
