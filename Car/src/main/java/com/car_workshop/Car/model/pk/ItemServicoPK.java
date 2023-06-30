package com.car_workshop.Car.model.pk;

import java.io.Serializable;
import java.util.Objects;

import com.car_workshop.Car.model.Peca_Carro;
import com.car_workshop.Car.model.Servico;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItemServicoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "peca_carro_id")
	private Peca_Carro peca_Carro;
	
	
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Peca_Carro getPeca_Carro() {
		return peca_Carro;
	}
	public void setPeca_Carro(Peca_Carro peca_Carro) {
		this.peca_Carro = peca_Carro;
	}
	@Override
	public int hashCode() {
		return Objects.hash(servico, peca_Carro);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemServicoPK other = (ItemServicoPK) obj;
		return Objects.equals(servico, other.servico) && Objects.equals(peca_Carro, other.peca_Carro);
	}	
}
