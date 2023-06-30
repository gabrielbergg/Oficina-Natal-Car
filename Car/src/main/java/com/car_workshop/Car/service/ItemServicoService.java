package com.car_workshop.Car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_workshop.Car.model.ItemServico;
import com.car_workshop.Car.model.pk.ItemServicoPK;
import com.car_workshop.Car.repository.ItemServicoRepository;

@Service
public class ItemServicoService {

	@Autowired
	ItemServicoRepository itens;
	
	public List<ItemServico> findAll() {
		List<ItemServico> list = itens.findAll();
		
		
		for (ItemServico it : list) {
			subTotal(it);
			itens.save(it);
		}
		
		return list;
	}
	
	public ItemServico findById(ItemServicoPK id) {
		
		ItemServico it = itens.findById(id).orElseThrow();
		
		return it;
	}
	
	public ItemServico create(ItemServico item) {
		ItemServico it = itens.save(item);
		return it;
	}
	
	public ItemServico update(ItemServico item) {
		ItemServico it = new ItemServico();
		
		it.setQuantidade(item.getQuantidade());
		it.setServico(item.getServico());
		it.setPeca_Carro(item.getPeca_Carro());
		
		return itens.save(it);
	}
	
	public void subTotal(ItemServico item) {
		double subTot = item.getPeca_Carro().getValor_peca() * item.getQuantidade();
		item.setsubTotal(subTot);
	}
}
