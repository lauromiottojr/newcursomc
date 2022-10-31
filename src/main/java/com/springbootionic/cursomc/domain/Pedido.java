package com.springbootionic.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// mascara para data
	@JsonFormat(pattern = "dd/MM/yyy HH:mm")
	private Date instante;
	
	// consultar melhor para que serve isso
	// @JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	// jsonmanagedreference permite que o pedido serialize, ou seja, acesse e lista todos os clientes
	//@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	// joincolumn é para setar o nome da chave extrangeira
	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco endercoDeEntrega;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Pedido() {		
		
	}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco endercoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.endercoDeEntrega = endercoDeEntrega;
	}
	
	public Double getValorTotal() {
		Double soma = 0d;
		for(ItemPedido ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndercoDeEntrega() {
		return endercoDeEntrega;
	}

	public void setEndercoDeEntrega(Endereco endercoDeEntrega) {
		this.endercoDeEntrega = endercoDeEntrega;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstado().getDesc());
		builder.append("\n Detalhes:\n");
		for(ItemPedido ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total:");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}
	
	

}
