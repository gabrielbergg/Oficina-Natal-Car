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

import com.car_workshop.Car.dto.v1.ClienteDto;
import com.car_workshop.Car.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes/v1")
@Tag(name = "Clientes", description = "Enderpoints para Clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Listar todos os clientes", description = "Listar todos os clientes",
	tags = {"Clientes"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = {
							@Content(
									///mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = ClienteDto.class))
									)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<ClienteDto> list = clienteService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Encontrar um cliente", description = "Encontrar um cliente",
	tags = {"Clientes"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ClienteDto.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClienteDto> findById(@PathVariable(value = "id") Long id) {
		ClienteDto entity = clienteService.findById(id);
		
		return ResponseEntity.ok().body(entity);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Adicionar um novo cliente", description = "Adicionar um novo cliente",
	tags = {"Clientes"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = @Content(schema = @Schema (implementation = ClienteDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto cliente) {
		cliente = clienteService.create(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes =  {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Atualizar os dados de um cliente", description = "Atualizar os dados de um cliente",
	tags = {"Clientes"},
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content = @Content(schema = @Schema (implementation = ClienteDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto cliente) {
		cliente = clienteService.update(id, cliente);
		
		return ResponseEntity.ok().body(cliente);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir um cliente", description = "Excluir um cliente",
	tags = {"Clientes"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}	
}