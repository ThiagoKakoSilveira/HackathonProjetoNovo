package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Cliente;
import com.stefanini.hackathon2.repositorios.ClienteRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

public class ClienteServico {
	@Inject
	private ClienteRepositorio repositorio;
	
	@Transacional
	public void salvar(Cliente cliente){
		if(cliente.getCpf()==null){			
			repositorio.inserir(cliente);
		}else{
			repositorio.atualizar(cliente);
		}		
	}
	
	@Transacional
	public List<Cliente> carregarTodasPessoasDoBanco(){
		return repositorio.todasPessoas();
	}
	
	@Transacional
	public void deletar(Cliente pessoa){
		repositorio.remover(pessoa);		
	}

}
