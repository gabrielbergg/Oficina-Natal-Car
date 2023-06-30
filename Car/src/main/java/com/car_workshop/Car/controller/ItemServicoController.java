package com.car_workshop.Car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_workshop.Car.dto.v1.ClienteDto;
import com.car_workshop.Car.model.ItemServico;
import com.car_workshop.Car.service.ItemServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/itens/v1")
@Tag(name = "Itens do Serviço", description = "Enderpoints para os Itens")
public class ItemServicoController {

	@Autowired
	ItemServicoService itens;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Listar todos os itens do serviço", description = "Listar todos os itens do serviço",
	tags = {"Itens do Serviço"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = {
							@Content(
									array = @ArraySchema(schema = @Schema(implementation = ItemServico.class))
									)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public List<ItemServico> findAll() {
		return itens.findAll();
	}
}
