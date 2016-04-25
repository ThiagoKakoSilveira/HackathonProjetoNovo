package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.servicos.LoginServico;

@ManagedBean
@SessionScoped
public class SessaoManagedBean {
	private String acesso, senha;
	
	@Inject
	private Login login;
	
	@Inject
	private LoginServico servico;
	
	public SessaoManagedBean(){
		
	}

	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public LoginServico getServico() {
		return servico;
	}

	public void setServico(LoginServico servico) {
		this.servico = servico;
	}
	
	public String logar() {
		List<Login> listaLoginsDoBanco = servico.carregarTodosLoginsDoBanco();
		for (Login loginDoBanco : listaLoginsDoBanco) {
			if (loginDoBanco.getAcesso().equals(acesso) && loginDoBanco.getSenha().equals(senha)) {
				login = loginDoBanco;
				login.setLogado(true);
				if (login.getAdmin() == true) {
					return "funcionario.xhtml?faces-redirect=true";
				}
				if (login.getEmprestimo() == true) {
					return "emprestimo.xhtml?faces-redirect=true";
				} else {
					return "livro.xhtml?faces-redirect=true";
				}
			}
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário e/ou senha inválidos.", null));
		return "telaLogin.xhtml?faces-redirect=true";
	}

	public String sair() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().invalidateSession();
		return "telaLogin.xhtml?faces-redirect=true";
	}

}
