package com.car_workshop.Car.dto.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.car_workshop.Car.model.Servico;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "nome", "telefone", "email", "modelo_carro"})
public class ClienteDto extends RepresentationModel<ClienteDto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id; 	
	private String nome;
	private String telefone;
	private String email;
	private String modelo_carro;
	
	private List<Servico> servico = new ArrayList<>();
	
	public ClienteDto() { 
	}

	public ClienteDto(Long id, String nome, String telefone, String email, String modelo_carro) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.modelo_carro = modelo_carro;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getModelo_carro() {
		return modelo_carro;
	}

	public void setModelo_carro(String modelo_carro) {
		this.modelo_carro = modelo_carro;
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
		ClienteDto other = (ClienteDto) obj;
		return Objects.equals(id, other.id);
	}
}
