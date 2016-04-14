package com.stefanini.hackathon2.managed.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
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
						
	}
	
	public void salvar(){
//		emprestimo.getLivro().setStatus(true);
		emprestimo.setPessoa(servico.pesquisarPorCpf(emprestimo.getPessoa().getCpf()));
		emprestimo.setDataEmprestimo(new Date());//arrumar o modelo... tirar o status de empr�stimo e colocar no livro arrumar o mapiamento do banco
		emprestimo.setStatus(true);		
		servico.salvar(getEmprestimo());
		Mensageiro.notificaInformacao("Parab�ns", "Empr�stimo cadastrado com sucesso!");
		carregarListaDeEmprestimo();
		limpar();
	}
	
	public void deletar(Emprestimo emprestimo){
		servico.deletar(emprestimo);
		Mensageiro.notificaInformacao("Parab�ns", "Empr�stimo deletado com sucesso!");
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
		getEmprestimo().setPessoa(new Pessoa());
//		getEmprestimo().setLivro(new Livro());
	}
	
	public void devolver(Emprestimo emprestimo){
		emprestimo.setDataDevolucao(new Date());
		emprestimo.setStatus(false);
		servico.salvar(emprestimo);
		Mensageiro.notificaInformacao("Parab�ns", "Empr�stimo devolvido com sucesso!");
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
