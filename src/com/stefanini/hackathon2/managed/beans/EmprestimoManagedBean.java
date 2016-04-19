package com.stefanini.hackathon2.managed.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Cliente;
import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.servicos.EmprestimoServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class EmprestimoManagedBean {
	@Inject
	private Emprestimo emprestimo;
	private List<Emprestimo>listaDeEmprestimos;
	
	@Inject
	private EmprestimoServico servico;
	
	public EmprestimoManagedBean(){
								
	}
	
	public void salvar(){
//		emprestimo.getLivro().setStatus(true);
		emprestimo.setCliente(servico.pesquisarPorCpf(emprestimo.getCliente().getCpf()));
		emprestimo.setDataEmprestimo(new Date());//arrumar o modelo... tirar o status de empréstimo e colocar no livro arrumar o mapiamento do banco
//		emprestimo.setStatus(true);		
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
		if(listaDeEmprestimos == null){
			carregarListaDeEmprestimo();
		}
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
		getEmprestimo().setCliente(new Cliente());
//		getEmprestimo().setLivro(new Livro());
	}
	
	public void devolver(Emprestimo emprestimo){
		emprestimo.setDataDevolucao(new Date());
		emprestimo.setStatus(false);
		servico.salvar(emprestimo);
		Mensageiro.notificaInformacao("Parabéns", "Empréstimo devolvido com sucesso!");
		carregarListaDeEmprestimo();
		limpar();
	}

	public EmprestimoServico getServico() {
		return servico;
	}

	public void setServico(EmprestimoServico servico) {
		this.servico = servico;
	}
	
	public void setListaDeEmprestimos(List<Emprestimo> listaDeEmprestimos) {
		this.listaDeEmprestimos = listaDeEmprestimos;
	}
	
}
