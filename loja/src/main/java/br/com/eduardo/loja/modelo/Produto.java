package br.com.eduardo.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//mapeamento das entidades

@Entity
@Table(name = "produtos")
@NamedQuery(name = "Produto.produtoPorCategoria",
query = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {

	// Indicar com o @Id qual � a Primary Key
	// Indicar com GeneratedValue que � um AUTO INCREMENT
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();
	
	//Indicar o tipo de relacionamento(cardinalidade), no caso, muitos para um
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	
	//Quando � usado o Enum
	//Caso n�o especifique para armazenar String vai ser armazenado o numero da ordem no Enum
	//@Enumerated(EnumType.STRING)
	//private Categoria categoria;
	
	
	//O JPA exige que tenha o construtor default
	public Produto() {
	}
	
	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
