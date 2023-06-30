package com.car_workshop.Car.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_workshop.Car.controller.ClienteController;
import com.car_workshop.Car.dto.v1.ClienteDto;
import com.car_workshop.Car.exceptions.ResouceNotFoundException;
import com.car_workshop.Car.mapper.ModellsMapper;
import com.car_workshop.Car.model.Cliente;
import com.car_workshop.Car.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;


	public List<ClienteDto> findAll() {
		List<ClienteDto> list =  ModellsMapper.parseListObj(clienteRepository.findAll(), ClienteDto.class);
		
		for (ClienteDto cl : list) {
			cl.add(linkTo(methodOn(ClienteController.class).findById(cl.getId())).withSelfRel());
		}
		
		return list;
	}

	public ClienteDto findById(Long id) {
		Cliente entity = clienteRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));
		ClienteDto dto = ModellsMapper.parseObj(entity, ClienteDto.class);
		
		dto.add(linkTo(methodOn(ClienteController.class).findAll()).withSelfRel());
		
		return dto;
	}

	public ClienteDto create(ClienteDto clienteDto) {
		Cliente entity = ModellsMapper.parseObj(clienteDto, Cliente.class);
		ClienteDto dto = ModellsMapper.parseObj(clienteRepository.save(entity), ClienteDto.class);
		dto.add(linkTo(methodOn(ClienteController.class).findAll()).withSelfRel());
		dto.add(linkTo(methodOn(ClienteController.class).findById(entity.getId())).withSelfRel());

		return dto;
	}

	public ClienteDto update(Long id, ClienteDto cliente) {
		Cliente entity = clienteRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));

		entity.setNome(cliente.getNome());
		entity.setTelefone(cliente.getTelefone());
		entity.setEmail(cliente.getEmail());
		entity.setModelo_carro(cliente.getModelo_carro());

		ClienteDto dto = ModellsMapper.parseObj(clienteRepository.save(entity), ClienteDto.class);
		dto.add(linkTo(methodOn(ClienteController.class).findAll()).withSelfRel());
		dto.add(linkTo(methodOn(ClienteController.class).findById(entity.getId())).withSelfRel());


		return dto;
	}

	public void delete(Long id) {
		var entity = clienteRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));
		clienteRepository.delete(entity);
	}

}
