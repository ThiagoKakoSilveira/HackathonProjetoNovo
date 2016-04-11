package com.stefanini.hackathon2.managed.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.entidades.Livro;
import com.stefanini.hackathon2.entidades.Pessoa;
import com.stefanini.hackathon2.servicos.EmprestimoServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class EmprestimoManagedBean {
	
	private Emprestimo emprestimo;
	private List<Emprestimo>listaDeEmprestimos;
	
	@Inject
	private EmprestimoServico servico;
	
	public EmprestimoManagedBean(){
		limpar();				
	}
	
	public void salvar(){
		emprestimo.setPessoa(servico.pesquisarPorCpf(emprestimo.getPessoa().getCpf()));
		emprestimo.setDataEmprestimo(new Date());//arrumar o modelo... tirar o status de empréstimo e colocar no livro arrumar o mapiamento do banco 		
		servico.salvar(getEmprestimo());
		Mensageiro.notificaInformacao("Parabéns", "Empréstimo cadastrado com sucesso!");
		carregarListaDeEmprestimo();
		limpar();
	}
	
	public void deletar(Emprestimo emprestimo){
		servico.deletar(emprestimo);
		Mensageiro.notificaInformacao("Parabéns", "Empréstimo deletado com sucesso!");
		carregarListaDeEmprestimo();
		limpar();
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public List<Emprestimo> getListaDeEmprestimos() {
		return listaDeEmprestimos;
	}

	public void setListaEmprestimos(List<Emprestimo> emprestimos) {
		this.listaDeEmprestimos = emprestimos;
	}
	
	public void carregarListaDeEmprestimo(){
		setListaEmprestimos(servico.carregarTodosEmprestimosDoBanco());
	}
	
	public void limpar(){
		setEmprestimo(new Emprestimo());
		getEmprestimo().setPessoa(new Pessoa());
		getEmprestimo().setLivro(new Livro());
	}
}
