package com.car_workshop.Car.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Peca_Carro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome_peca;
	private String garantia;
	private Double valor_peca;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.peca_Carro", fetch = FetchType.LAZY) //cascade = CascadeType.REMOVE
	private List<ItemServico> itenServico = new ArrayList<>();
	
	public Peca_Carro() {
	}

	public Peca_Carro(Long id, String nome_peca, String garantia, Double valor_peca) {
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

//	public List<ItemServico> getItemServico() {
//		return itenServico;
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
		Peca_Carro other = (Peca_Carro) obj;
		return Objects.equals(id, other.id);
	}
}
