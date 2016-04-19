package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Cliente;
import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.repositorios.EmprestimoRepositorio;
import com.stefanini.hackathon2.repositorios.ClienteRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;
import com.stefanini.hackathon2.util.Mensageiro;

public class EmprestimoServico {
	@Inject
	private EmprestimoRepositorio repositorioDeEmprestimo;
	
	@Inject
	private ClienteRepositorio repositorioDePessoa;
	
	@Transacional
	public void salvar(Emprestimo emprestimo){		
		if(emprestimo.getId() == null){
			if (existePessoaComCpf(emprestimo)) {
				repositorioDeEmprestimo.inserir(emprestimo);					
			} else {
				String mensagem = "Cliente com cpf " + emprestimo.getCliente().getCpf() + " não existe.";
				Mensageiro.nootificaErro("Erro!", mensagem);
			}
		}else{
			repositorioDeEmprestimo.atualizar(emprestimo);
		}
	}

	private boolean existePessoaComCpf(Emprestimo emprestimo) {
		return repositorioDePessoa.existePessoaComCpf(emprestimo.getCliente().getCpf());
	}
	
	public Cliente pesquisarPorCpf(String cpf){
		return repositorioDePessoa.pesquisarPorCpf(cpf);
	}
	
	@Transacional
	public List<Emprestimo>carregarTodosEmprestimosDoBanco(){
		return repositorioDeEmprestimo.todosEmprestimos();			
	}
	
	@Transacional
	public void deletar(Emprestimo emprestimo){
		repositorioDeEmprestimo.remover(emprestimo);
	}
	
//	@Transacional
//	public void devolver(Emprestimo emprestimo){
//		repositorioDeEmprestimo.atualizar(emprestimo);
//	}

}
