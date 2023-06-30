package com.car_workshop.Car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_workshop.Car.dto.v1.security.AccountCredentialDto;
import com.car_workshop.Car.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authetication enderponts")
public class AuthController {

	@Autowired
	AuthService authService;
	
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialDto data) {
		if(checkIfParamsIsNotNull(data)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalidiiiii client request!");
		}
		var token = authService.signin(data);
		
		if(token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid clientes request!");
		}
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Refresh Token for autheticated a user and returns a token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		if(checkIffParamsIsNotNull(username, refreshToken)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalidiiiii client request!");
		}
		var token = authService.refreshToken(username, refreshToken);
		
		if(token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid clientes request!");
		}
		return token;
	}
	
	private boolean checkIfParamsIsNotNull(AccountCredentialDto data) {
		return data == null || data.getUserName() == null || data.getUserName().isBlank()
				 || data.getPassword() == null || data.getPassword().isBlank();
	}
	
	private boolean checkIffParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank()
				|| username == null || username.isBlank();
	}
}
