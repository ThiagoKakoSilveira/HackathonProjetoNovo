package com.stefanini.hackathon2.servicos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Cliente;
import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.entidades.Livro;
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
				if (!emprestimo.isStatus()) {
					for (Livro livroVerificaEstoque : emprestimo.getLivros()) {
						if (livroVerificaEstoque.getQuantidadeEstoque() <= 2) {
							String mensagemLivro = "O livro "+livroVerificaEstoque.getNome()+" Contém apenas dois e não podem ser emprestados";
							Mensageiro.nootificaErro("Erro!", mensagemLivro);
						} else {
							for (Livro livroDiminuirEstoque : emprestimo.getLivros()) {
								livroDiminuirEstoque.setQuantidadeEstoque(livroDiminuirEstoque.getQuantidadeEstoque() - 1);
							}
							emprestimo.setStatus(true);
							emprestimo.setDataEmprestimo(LocalDateTime.now());
							repositorioDeEmprestimo.inserir(emprestimo);
						}
					}
				}
			} else {
				String mensagem = "Cliente com cpf " + emprestimo.getCliente().getCpf() + " Não existe esse Cliente cadastrado";
				Mensageiro.nootificaErro("Erro!", mensagem);
			}
			
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
	
	@Transacional
	public void devolver(Emprestimo emprestimo){
		if(emprestimo.isStatus()){
			emprestimo.setStatus(false);
			emprestimo.setDataDevolucao(LocalDateTime.now());
//			int dias = Days.daysBetween(emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao()).getDays(); ver como fazer isso depois
			Duration dur = Duration.between(emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
			for (Livro livroDevolvido : emprestimo.getLivros()) {
				livroDevolvido.setQuantidadeEstoque(livroDevolvido.getQuantidadeEstoque()+1);				
			}
			if(dur.toDays() > 7){
				emprestimo.setDiasAtrasados((int) dur.toDays() - 7);
				repositorioDeEmprestimo.atualizar(emprestimo);
			} else{
				emprestimo.setDiasAtrasados(0);
				repositorioDeEmprestimo.atualizar(emprestimo);
			}
		}
	}

}
