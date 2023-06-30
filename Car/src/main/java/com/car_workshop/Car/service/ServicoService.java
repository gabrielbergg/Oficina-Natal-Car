package com.car_workshop.Car.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_workshop.Car.controller.ClienteController;
import com.car_workshop.Car.controller.PecaController;
import com.car_workshop.Car.controller.ServicoController;
import com.car_workshop.Car.dto.v1.ServicosDto;
import com.car_workshop.Car.exceptions.ResouceNotFoundException;
import com.car_workshop.Car.mapper.ModellsMapper;
import com.car_workshop.Car.model.ItemServico;
import com.car_workshop.Car.model.Servico;
import com.car_workshop.Car.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	ServicoRepository servicoRepository;
	
	@Autowired
	ItemServicoService itemService;

	public List<ServicosDto> findAll() {
		List<ServicosDto> list = ModellsMapper.parseListObj(servicoRepository.findAll(), ServicosDto.class);
		Servico servic = new Servico();
		
		itemService.findAll();
		
		for (ServicosDto ser : list) {
			calcularValorTotal(ser);
			servic = ModellsMapper.parseObj(ser, Servico.class);
			ModellsMapper.parseObj(servicoRepository.save(servic), ServicosDto.class);
		}
		
		for (ServicosDto serv : list) {
			serv.add(linkTo(methodOn(ServicoController.class).findById(serv.getId())).withSelfRel());
			serv.getCliente().add(linkTo(methodOn(ClienteController.class).
					findById(serv.getCliente().getId())).withSelfRel());
			
			for (ItemServico it : serv.getItens()) {
				it.add(linkTo(methodOn(PecaController.class).findById(it.getPeca_Carro().getId())).withSelfRel());
			}
		}
		
		
		
		return list;
	}

	public ServicosDto findById(Long id) {
		Servico entity = servicoRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));

		ServicosDto dto = ModellsMapper.parseObj(entity, ServicosDto.class);
		dto.add(linkTo(methodOn(ServicoController.class).findAll()).withSelfRel());
		dto.getCliente().add(linkTo(methodOn(ClienteController.class).
				findById(dto.getCliente().getId())).withSelfRel());
		
		
		for (ItemServico it : entity.getItens()) {
			it.add(linkTo(methodOn(PecaController.class).findById(it.getPeca_Carro().getId())).withSelfRel());
		}
		
		return dto;
	}
	

	public ServicosDto create(ServicosDto servico) {
		
		Servico entity = ModellsMapper.parseObj(servico, Servico.class);
		entity.setCliente(entity.getCliente());
		
		entity.setItens(entity.getItens());
		ServicosDto dto = ModellsMapper.parseObj(servicoRepository.save(entity), ServicosDto.class);
		dto.add(linkTo(methodOn(ServicoController.class).findAll()).withSelfRel());
		dto.add(linkTo(methodOn(ServicoController.class).findById(entity.getId())).withSelfRel());
		
		for (ItemServico it : entity.getItens()) {
			it.setServico(entity);
			itemService.create(it);
		}
		
		return dto;
	}

	
	public ServicosDto update(Long id, ServicosDto servico) {
		Servico entity = servicoRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));

		entity.setDate(servico.getDate());
		entity.setCliente(servico.getCliente());
		
		for (ItemServico it : servico.getItens()) {
			it.setServico(entity);
			itemService.create(it);
			entity.getItens().add(it);
		}

		ServicosDto dto = ModellsMapper.parseObj(servicoRepository.save(entity), ServicosDto.class);
		dto.add(linkTo(methodOn(ServicoController.class).findAll()).withSelfRel());
		dto.add(linkTo(methodOn(ServicoController.class).findById(entity.getId())).withSelfRel());

		return dto;
	}

	public void delete(Long id) {
		servicoRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Erro. Id não encontrado."));
		servicoRepository.deleteById(id);
	}
	
	

	private void calcularValorTotal(ServicosDto servico) {
		Double valorTotal = 0.0;
		//servico.getPecas().stream().mapToDouble(Peca_Carro::getValor_peca).sum();
//		servico.setValor_total(valorTotal);

        for (ItemServico p : servico.getItens()) {
        	valorTotal += p.getsubTotal();
		}
        servico.setTotal(valorTotal);
	}
}