package com.car_workshop.Car.dto.v1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.car_workshop.Car.model.Cliente;
import com.car_workshop.Car.model.ItemServico;

public class ServicosDto extends RepresentationModel<ServicosDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDate date;
	private Double total;
	private Cliente cliente;
	
	private Set<ItemServico> itens = new HashSet<>();
	
	public ServicosDto() {
	}

	public ServicosDto(Long id, LocalDate date, Cliente cliente) {
		this.id = id;
		this.date = date;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Set<ItemServico> getItens() {
		return itens;
	}
	
	public void addItens(ItemServico item) {
		itens.add(item);
	}

	public void setItens(Set<ItemServico> itens) {
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
		ServicosDto other = (ServicosDto) obj;
		return Objects.equals(id, other.id);
	}
}