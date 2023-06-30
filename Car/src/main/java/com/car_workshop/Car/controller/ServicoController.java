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

import com.car_workshop.Car.dto.v1.ServicosDto;
import com.car_workshop.Car.service.ServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/serviços/v1")
@Tag(name = "Serviços", description = "Enderpoints para os Serviços")
public class ServicoController {

	@Autowired
	ServicoService servicoService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Listar todos os serviços", description = "Listar todos os serviços, o cliente e as peças associadas a ele", tags = {
			"Serviços" }, responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = {
							@Content(array = @ArraySchema(schema = @Schema(implementation = ServicosDto.class))) }),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) 
	})
	public ResponseEntity<List<ServicosDto>> findAll() {
		List<ServicosDto> list = servicoService.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Encontrar um serviço", description = "Encontrar um serviço, o cliente e as peças associadas a ele", tags = {
			"Serviços" }, responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = ServicosDto.class))),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<ServicosDto> findById(@PathVariable(value = "id") Long id) {
		ServicosDto entity = servicoService.findById(id);

		return ResponseEntity.ok().body(entity);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Adicionar um novo serviço", description = "Adicionar um novo serviço, associar um cliente a ele, e criar os itens do serviço", tags = {
			"Serviços" }, responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = ServicosDto.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<ServicosDto> create(@RequestBody ServicosDto servico) {
		servico = servicoService.create(servico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId())
				.toUri();

		return ResponseEntity.created(uri).body(servico);
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Atualizar os dados de um serviço", description = "Atualizar os dados de um serviço, mudar o cliente e os itens do serviço",
	tags = {"Serviços"},
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content = @Content(schema = @Schema (implementation = ServicosDto.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ServicosDto> update(@PathVariable Long id, @RequestBody ServicosDto servico) {
		servico = servicoService.update(id, servico);

		return ResponseEntity.ok().body(servico);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir um serviço", description = "Excluir um serviço, e os itens associados a ele",
	tags = {"Serviços"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		servicoService.delete(id);

		return ResponseEntity.noContent().build();
	}

}
