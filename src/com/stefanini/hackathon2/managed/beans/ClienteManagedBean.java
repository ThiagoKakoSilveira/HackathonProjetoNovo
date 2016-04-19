package com.stefanini.hackathon2.managed.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Cliente;
import com.stefanini.hackathon2.servicos.ClienteServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class ClienteManagedBean {
	
	private Cliente cliente;
	private List<Cliente> listaClientes;
	
	@Inject
	private ClienteServico servico;
	
	public ClienteManagedBean(){
		
	}
	
	public void salvar(){
		servico.salvar(getCliente());
		Mensageiro.notificaInformacao("Parabéns!", "Cliente cadastrada com sucesso!");
		carregaListaDeClientes();
		limpar();
	}
	
	public void deletar(Cliente cliente){
		servico.deletar(cliente);
		Mensageiro.notificaInformacao("Parabéns!", "Cliente deletada com sucesso");
		carregaListaDeClientes();
		limpar();
	}

	public void limpar() {
		setCliente(new Cliente());		
	}

	private void carregaListaDeClientes() {
		setListaClientes(servico.carregarTodasPessoasDoBanco());	
	}

	public Cliente getCliente() {
		if(cliente == null){
			limpar();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		if(listaClientes == null){
			carregaListaDeClientes();
		}
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}	
}
