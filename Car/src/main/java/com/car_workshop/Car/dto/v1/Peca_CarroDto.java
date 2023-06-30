package com.car_workshop.Car.dto.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.car_workshop.Car.model.ItemServico;

public class Peca_CarroDto extends RepresentationModel<Peca_CarroDto> implements Serializable {
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome_peca;
	private String garantia;
	private Double valor_peca;
	
	private List<ItemServico> itens = new ArrayList<>();
	
	public Peca_CarroDto() {
	}

	public Peca_CarroDto(Long id, String nome_peca, String garantia, Double valor_peca) {
		this.id = id;
		this.nome_peca = nome_peca;
		this.garantia = garantia;
		this.valor_peca = valor_peca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_peca() {
		return nome_peca;
	}

	public void setNome_peca(String nome_peca) {
		this.nome_peca = nome_peca;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public Double getValor_peca() {
		return valor_peca;
	}

	public void setValor_peca(Double valor_peca) {
		this.valor_peca = valor_peca;
	}

//	public List<ItemServico> getServico() {
//		return itens;
//	}

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
		Peca_CarroDto other = (Peca_CarroDto) obj;
		return Objects.equals(id, other.id);
	}
}
