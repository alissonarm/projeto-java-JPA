package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class TesteEstadosEntidade {

	public static void main(String[] args) {

		Categoria celular = new Categoria("CELULAR");
		
		EntityManager em = JPAUtil.getEntityManager();
	
		em.getTransaction().begin();
		celular.setNome("xpto");
		
		em.flush();
		em.clear();
		
		celular = em.merge(celular);
		celular.setNome("1234");
		
		em.flush();
		em.remove(celular);
		em.flush();
		
		 

		em.getTransaction().commit();
		em.close();
		
	}

}
