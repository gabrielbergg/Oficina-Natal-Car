package com.car_workshop.Car.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.car_workshop.Car.model.pk.ItemServicoPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_servico")
public class ItemServico extends RepresentationModel<ItemServico> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemServicoPK id = new ItemServicoPK();
	
	private Double subTotal;
	private Integer quantidade;
	
	
	public ItemServico() {
	}

	public ItemServico(Servico servico, Peca_Carro peca, Double subTotal, Integer quantidade) {
		id.setServico(servico);
		id.setPeca_Carro(peca);
		this.subTotal = subTotal;
		this.quantidade = quantidade;
	}

	@JsonIgnore
	public Servico getServico() {
		return id.getServico();
	}
	
	public void setServico(Servico servico) {
		id.setServico(servico);
	}
	
	public Peca_Carro getPeca_Carro() {
		return id.getPeca_Carro();
	}
	
	public void setPeca_Carro(Peca_Carro peca) {
		id.setPeca_Carro(peca);
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getsubTotal() {
		return subTotal;
	}

	public void setsubTotal(Double subTotal) {
		this.subTotal = subTotal;
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
		ItemServico other = (ItemServico) obj;
		return Objects.equals(id, other.id);
	}
}