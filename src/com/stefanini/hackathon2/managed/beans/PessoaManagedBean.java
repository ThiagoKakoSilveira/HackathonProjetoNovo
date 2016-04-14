package com.stefanini.hackathon2.managed.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Pessoa;
import com.stefanini.hackathon2.servicos.PessoaServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class PessoaManagedBean {
	
	private Pessoa pessoa;
	private List<Pessoa> listaPessoas;
	
	@Inject
	private PessoaServico servico;
	
	public PessoaManagedBean(){
		
	}
	
	public void salvar(){
		servico.salvar(getPessoa());
		Mensageiro.notificaInformacao("Parabéns!", "Cliente cadastrada com sucesso!");
		carregaListaDePessoas();
		limpar();
	}
	
	public void deletar(Pessoa pessoa){
		servico.deletar(pessoa);
		Mensageiro.notificaInformacao("Parabéns!", "Cliente deletada com sucesso");
		carregaListaDePessoas();
		limpar();
	}

	public void limpar() {
		setPessoa(new Pessoa());		
	}

	private void carregaListaDePessoas() {
		setListaPessoas(servico.carregarTodasPessoasDoBanco());	
	}

	public Pessoa getPessoa() {
		if(pessoa == null){
			limpar();
		}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaPessoas() {
		if(listaPessoas == null){
			carregaListaDePessoas();
		}
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}	
}
