package com.udemy.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;

	//@JsonBackReference //Define que do outro lado da associação foi buscado os objetos, então não precisa buscar novamente
	@JsonIgnore
	@ManyToMany //Muitos pra muitos
	@JoinTable(name = "PRODUTO_CATEGORIA", //Anotation para criar uma tabela de relacionamento cujo o nome será PRODUTO_CATEGORIA
		joinColumns = @JoinColumn(name = "produto_id"), //Definindo quem é a chave primária dessa tabela
		inverseJoinColumns = @JoinColumn(name = "categoria_id") //Definindo quem é a chave estrangeira dessa tabela
	) 
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>(); //Definindo a colletion como SET para não permitir que tenha item repetido no mesmo pedido

	public Produto() {}

	public Produto(Integer id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	
	@JsonIgnore //Não serializar essa lista de pedidos.
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		itens.stream().map(item -> lista.add(item.getPedido()));
//		for (ItemPedido ItemPedido : itens) {
//			lista.add(ItemPedido.getPedido());
//		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
}
