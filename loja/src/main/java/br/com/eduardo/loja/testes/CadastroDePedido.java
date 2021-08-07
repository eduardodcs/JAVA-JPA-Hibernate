package br.com.eduardo.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.internal.build.AllowSysOut;

import br.com.eduardo.loja.dao.CategoriaDao;
import br.com.eduardo.loja.dao.ClienteDao;
import br.com.eduardo.loja.dao.PedidoDao;
import br.com.eduardo.loja.dao.ProdutoDao;
import br.com.eduardo.loja.modelo.Categoria;
import br.com.eduardo.loja.modelo.Cliente;
import br.com.eduardo.loja.modelo.ItemPedido;
import br.com.eduardo.loja.modelo.Pedido;
import br.com.eduardo.loja.modelo.Produto;
import br.com.eduardo.loja.util.JPAUtil;
import br.com.eduardo.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(4, pedido, produto2));
		pedido.adicionarItem(new ItemPedido(20, pedido, produto3));
	
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Total vendido: " + totalVendido);

		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
			relatorio.forEach(System.out::println);		
	}

	
	private static void popularBancoDeDados() {
		
		//Esta parte do código é somente simulaçao de um cadastro
		Categoria celulares = new Categoria("CELULARES");
		Categoria notebooks = new Categoria("INFORMATICA");
		Categoria canetas = new Categoria("PAPELARIA");
		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto notebook = new Produto("Samsung", "14 polegadas", new BigDecimal("1200"), notebooks);
		Produto caneta = new Produto("Caneta BIC", "Caneta Azul", new BigDecimal("2.5"), canetas);
		
		Cliente cliente = new Cliente("Eduardo", "12312312356");
		
		EntityManager em = JPAUtil.getEntityManager();		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(notebooks);
		categoriaDao.cadastrar(canetas);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(notebook);
		produtoDao.cadastrar(caneta);
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}
}
