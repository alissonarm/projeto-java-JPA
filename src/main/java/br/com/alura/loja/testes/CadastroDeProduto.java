package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getDescricao());
		
		System.out.println(produtoDao.buscarTodos());
		
		List<Produto> todos = produtoDao.buscarTodos();
		
		for (Produto produto : todos) {
			System.out.println(produto.getNome());
		}

		List<Produto> nomes = produtoDao.buscarPorNome("Xiaomi Redmi");
		nomes.forEach(p3 -> System.out.println("1 ==> " + p3.getDataCadastro()));
		
		List<Produto> nomes2 = produtoDao.pesquisarPorNome("Xiaomi Redmi");
		nomes2.forEach(p2 -> System.out.println("2 ==> " + p2.getDataCadastro()));
		
		List<Produto> nomes3 = produtoDao.filtrarPelaCategoria("CELULAR");
		nomes3.forEach(p4 -> System.out.println("3 ==> " + p4.getDescricao()));

		BigDecimal preco = produtoDao.buscarPrecoPorNome("Xiaomi Redmi");
		System.out.println("4 ==> " + preco);

		
	}

	private static void cadastrarProduto() {
		Categoria celular = new Categoria("CELULAR");
		Produto produto = new Produto("Xiaomi Redmi", "Celular chinês" , new BigDecimal("900"), celular );
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
	
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celular); 
		produtoDao.cadastrar(produto); 

		em.getTransaction().commit();
		em.close();
	}

}
