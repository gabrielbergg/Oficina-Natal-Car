package com.car_workshop.Car.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.car_workshop.Car.dto.v1.Peca_CarroDto;
import com.car_workshop.Car.service.PecaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/peças/v1")
@Tag(name = "Peças", description = "Enderpoints para Peças")
public class PecaController {

	@Autowired
	PecaService pecaService;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Listar todas as peças", description = "Listar todas as peças",
	tags = {"Peças"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = {
							@Content(
									///mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = Peca_CarroDto.class))
									)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<List<Peca_CarroDto>> findAll() {
		List<Peca_CarroDto> list = pecaService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Encontrar uma peça", description = "Encontrar uma peça",
	tags = {"Peças"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = Peca_CarroDto.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Peca_CarroDto> findById(@PathVariable(value = "id") Long id) {
		Peca_CarroDto entity = pecaService.findById(id);
		
		return ResponseEntity.ok().body(entity);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Adicionar uma peça nova", description = "Adicionar uma peça nova",
	tags = {"Peças"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = @Content(schema = @Schema (implementation = Peca_CarroDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Peca_CarroDto> create(@RequestBody Peca_CarroDto peca_carro) {
		peca_carro = pecaService.create(peca_carro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(peca_carro.getId()).toUri();
		
		return ResponseEntity.created(uri).body(peca_carro);
	}
	
	@PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Atualizar os dados de uma peça", description = "Atualizar os dados de uma peça",
	tags = {"Peças"},
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content = @Content(schema = @Schema (implementation = Peca_CarroDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<Peca_CarroDto> update(@PathVariable Long id, @RequestBody Peca_CarroDto peca_carro) {
		peca_carro = pecaService.update(id, peca_carro);
		
		return ResponseEntity.ok().body(peca_carro);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir uma peça", description = "Excluir uma peça",
	tags = {"Peças"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		pecaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
