package com.stefanini.hackathon2.entidades;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="cliente")
//	private List<Emprestimo> emprestimos;
//
//	public List<Emprestimo> getEmprestimos() {
//		return emprestimos;
//	}
//
//	public void setEmprestimos(List<Emprestimo> emprestimos) {
//		this.emprestimos = emprestimos;
//	}

		
}
