package com.stefanini.hackathon2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Funcionario extends Pessoa {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Integer id;
	
	@Column(nullable=false)
	private double salario;
	
	@OneToOne(optional=false)
	@JoinColumn(nullable=false)
	private Login login;

	public Funcionario() {
		super();		
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
