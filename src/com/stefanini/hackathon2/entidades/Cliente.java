package com.stefanini.hackathon2.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
@Entity
public class Cliente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="cliente")
	private List<Emprestimo> emprestimos;

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((emprestimos == null) ? 0 : emprestimos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (emprestimos == null) {
			if (other.emprestimos != null)
				return false;
		} else if (!emprestimos.equals(other.emprestimos))
			return false;
		return true;
	}
	
	
}
