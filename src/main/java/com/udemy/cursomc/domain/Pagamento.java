package com.udemy.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.cursomc.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //(Mapeamento de Herança no banco de dados) Define a criação de uma tabela única com as subTabelas inclusas ou separa cada subTabela em uma tabela específica
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name = "id")
	private Integer id;
	private Integer estado;
	
	//@JsonBackReference
	@JsonIgnore
	@OneToOne
	@MapsId //Indica que os valores da chave primária serão copiados da entidade Pedido
	@JoinColumn(name = "pedido_id") //Referenciando pedido.
	private Pedido pedido;
	
	public Pagamento() {}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCod(); //Se vier um estado do DTO nulo, ele vai ser inserido nulo, ou se tiver valor, insere o valor que chegou.
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
}
