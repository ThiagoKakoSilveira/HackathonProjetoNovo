package com.stefanini.hackathon2.repositorios;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.stefanini.hackathon2.entidades.Funcionario;
import com.stefanini.hackathon2.entidades.Login;

@SuppressWarnings("all")
public class FuncionarioRepositorio {
	
	@Inject
	private EntityManager entityManager;
	
	public void inserir(Funcionario funcionario){
		entityManager.persist(funcionario);
	}
//	public void inserirLogin(Login login){
//		entityManager.persist(login);
//	}
	
	public List<Funcionario> todosFuncionarios(){
		return entityManager.createQuery("select f from "+Funcionario.class.getSimpleName() + " f").getResultList();
	}
	
	public void remover(Funcionario funcionario){
		entityManager.remove(entityManager.merge(funcionario));
	}
	
	public void removerPorId(Integer id){
		Funcionario entidade = entityManager.find(Funcionario.class, id);
		entityManager.remove(entidade);
	}
	
	public void atualizar(Funcionario funcionario){
		entityManager.merge(funcionario);
	}
	
	public Funcionario pesquisarPorId(Integer id){
		return entityManager.find(Funcionario.class, id);
	}
	
	public Funcionario pesquisarPorCpf(String cpf){
		String sql = "select f from " + Funcionario.class.getSimpleName() + "f where f.cpf = '"+ cpf + "'";
		return (Funcionario) entityManager.createQuery(sql).getSingleResult();
	}
	
	public boolean existeFuncionarioComCpf(String cpf){
		String sql = "select count(1) from " + Funcionario.class.getSimpleName() + "f where f.cpf = '"+ cpf + "'";
		long quantidade = (long) entityManager.createQuery(sql).getSingleResult();
		return (quantidade > 0);
	}
	public void atualizarLogin(Login login) {
		entityManager.merge(login);		
	}
}
