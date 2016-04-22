package com.stefanini.hackathon2.managed.beans;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Funcionario;
//import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.servicos.FuncionarioServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class FuncionarioManagedBean {
	
	private Funcionario funcionario;
	private List<Funcionario> listaFuncionarios;
	
	@Inject
	private FuncionarioServico servico;
	
//	@Inject
//	private Login login;
	
	public FuncionarioManagedBean(){
		
	}
	
	public void salvar(){
//		servico.salvarLogin(getFuncionario().getLogin());
//		funcionario.setLogin(login);
		
		servico.salvar(getFuncionario());
		Mensageiro.notificaInformacao("Parabéns", "Funcionário cadastrado com sucesso!");
		carregarListaDeFuncionarios();
		limpar();
		
	}
	
	public void deletar(){
		servico.deletar(getFuncionario());
		Mensageiro.notificaInformacao("Parabéns", "Funcionário cadastrado com sucesso!");
		carregarListaDeFuncionarios();
		limpar();
	}
	
	public void limpar(){
		setFuncionario(new Funcionario());
	}
	
	public void carregarListaDeFuncionarios(){
		setListaFuncionarios(servico.carregaTodosFuncionarioDoBanco());
	}

	public Funcionario getFuncionario() {
		if(funcionario == null){
			limpar();
		}
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionarios() {
		if(listaFuncionarios==null){
			carregarListaDeFuncionarios();
		}
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
}
