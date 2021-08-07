package br.com.eduardo.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.eduardo.loja.dao.CategoriaDao;
import br.com.eduardo.loja.dao.ProdutoDao;
import br.com.eduardo.loja.modelo.Categoria;
import br.com.eduardo.loja.modelo.Produto;
import br.com.eduardo.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarProdutos();
		todos.forEach(p2 -> System.out.println("Buscar todos: " + p2.getNome()));
		
		List<Produto> xiaomi = produtoDao.buscarPorNome("Xiaomi Redmi");
		xiaomi.forEach(p3 -> System.out.println("Buscar por nome: " + p3.getNome()));
		
		List<Produto> celulares = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		celulares.forEach(p3 -> System.out.println("Buscar por nome da Categoria: " + p3.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("Preço do Produto: " + precoDoProduto);
	}
	
	

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		
		
		//Esta parte do código é somente simulaçao de um cadastro
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
