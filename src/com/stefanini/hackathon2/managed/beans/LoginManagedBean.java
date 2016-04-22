package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.servicos.LoginServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class LoginManagedBean {
	
	private Login login;
	private List<Login>listaLogin;
	
	@Inject
	private LoginServico servico;
	
	public LoginManagedBean(){
//		login = new Login();
	}
	
	public void salvar(){
		servico.salvar(getLogin());
		Mensageiro.notificaInformacao("Parabéns", "Login cadastrado com sucesso!");
		carregarListaDeLogins();
		limpar();
	}
	
	public void deletar(){
		servico.deletar(getLogin());
		Mensageiro.notificaInformacao("Parabéns", "Login cadastrado com sucesso!");
		carregarListaDeLogins();
		limpar();
	}
	

	private void carregarListaDeLogins() {
		setListaLogin(servico.carregarTodosLoginsDoBanco());		
	}

	private void limpar() {
		setLogin(new Login());		
	}

	public Login getLogin() {
		if(login == null){
			limpar();
		}
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Login> getListaLogin() {
		if(listaLogin == null){
			carregarListaDeLogins();
		}
		return listaLogin;
	}

	public void setListaLogin(List<Login> listaLogin) {
		this.listaLogin = listaLogin;
	}

	public LoginServico getServico() {
		return servico;
	}

	public void setServico(LoginServico servico) {
		this.servico = servico;
	}
	
}
