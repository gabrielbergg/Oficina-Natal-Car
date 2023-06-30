package com.car_workshop.Car.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_workshop.Car.controller.PecaController;
import com.car_workshop.Car.dto.v1.Peca_CarroDto;
import com.car_workshop.Car.exceptions.ResouceNotFoundException;
import com.car_workshop.Car.mapper.ModellsMapper;
import com.car_workshop.Car.model.Peca_Carro;
import com.car_workshop.Car.repository.PecaRespository;

@Service
public class PecaService {

	@Autowired
	PecaRespository pecaRespository;

	public List<Peca_CarroDto> findAll() {
		List<Peca_CarroDto> list = ModellsMapper.parseListObj(pecaRespository.findAll(), Peca_CarroDto.class);
		
		for (Peca_CarroDto pc : list) {
			pc.add(linkTo(methodOn(PecaController.class).findById(pc.getId())).withSelfRel());
		}
		
		return list;
	}

	public Peca_CarroDto findById(Long id) {
		Peca_Carro entity = pecaRespository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));
		Peca_CarroDto dto = ModellsMapper.parseObj(entity, Peca_CarroDto.class);
		
		dto.add(linkTo(methodOn(PecaController.class).findById(entity.getId())).withSelfRel());
		dto.add(linkTo(methodOn(PecaController.class).findAll()).withSelfRel());


		
		return dto;
	}

	public Peca_CarroDto create(Peca_CarroDto peca_Carro) {
		Peca_Carro entity = ModellsMapper.parseObj(peca_Carro, Peca_Carro.class);
		Peca_CarroDto dto = ModellsMapper.parseObj(pecaRespository.save(entity), Peca_CarroDto.class);
		
		dto.add(linkTo(methodOn(PecaController.class).findById(entity.getId())).withSelfRel());
		return dto;
	}

	public Peca_CarroDto update(Long id, Peca_CarroDto peca_Carro) {
		Peca_Carro entity = pecaRespository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));

		entity.setNome_peca(peca_Carro.getNome_peca());
		entity.setGarantia(peca_Carro.getGarantia());
		entity.setValor_peca(peca_Carro.getValor_peca());

		Peca_CarroDto dto = ModellsMapper.parseObj(pecaRespository.save(entity), Peca_CarroDto.class) ;
		dto.add(linkTo(methodOn(PecaController.class).findById(entity.getId())).withSelfRel());
		
		return dto;
	}

	public void delete(Long id) {
		pecaRespository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));
		pecaRespository.deleteById(id);
	}
}
