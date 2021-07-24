package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nm) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :VarNome";
		return em.createQuery(jpql, Produto.class).setParameter("VarNome", nm).getResultList();
	}
	
	public List<Produto> pesquisarPorNome(String nm) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
		return em.createQuery(jpql, Produto.class).setParameter(1, nm).getResultList();
	}
	
	public List<Produto> filtrarPelaCategoria(String categoria) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :categoria";
		return em.createQuery(jpql, Produto.class).setParameter("categoria", categoria).getResultList();
	}

	public BigDecimal buscarPrecoPorNome(String nm) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
		return em.createQuery(jpql, BigDecimal.class).setParameter(1, nm).getSingleResult();
	}

}
