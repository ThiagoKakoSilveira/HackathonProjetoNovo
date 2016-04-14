package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.entidades.Pessoa;
import com.stefanini.hackathon2.repositorios.EmprestimoRepositorio;
import com.stefanini.hackathon2.repositorios.PessoaRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;
import com.stefanini.hackathon2.util.Mensageiro;

public class EmprestimoServico {
	@Inject
	private EmprestimoRepositorio repositorioDeEmprestimo;
	
	@Inject
	private PessoaRepositorio repositorioDePessoa;
	
	@Transacional
	public void salvar(Emprestimo emprestimo){		
		if(emprestimo.getId() == null){
			if (existePessoaComCpf(emprestimo)) {
				repositorioDeEmprestimo.inserir(emprestimo);					
			} else {
				String mensagem = "Pessoa com cpf " + emprestimo.getPessoa().getCpf() + " não existe.";
				Mensageiro.nootificaErro("Erro!", mensagem);
			}
		}else{
			repositorioDeEmprestimo.atualizar(emprestimo);
		}
	}

	private boolean existePessoaComCpf(Emprestimo emprestimo) {
		return repositorioDePessoa.existePessoaComCpf(emprestimo.getPessoa().getCpf());
	}
	
	public Pessoa pesquisarPorCpf(String cpf){
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
