package com.car_workshop.Car.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente extends RepresentationModel<Cliente> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "telefone", nullable = false)
	private String telefone;
	private String email;
	
	@Column(name = "modelo_carro", nullable = false)
	private String modelo_carro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")  ///cascade = CascadeType.REMOVE
	private List<Servico> servico = new ArrayList<>();
	
	public Cliente() { 
	}

	public Cliente(Long id, String nome, String telefone, String email, String modelo_carro) {
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
}