package com.car_workshop.Car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.car_workshop.Car.dto.v1.security.AccountCredentialDto;
import com.car_workshop.Car.dto.v1.security.TokenDto;
import com.car_workshop.Car.model.User;
import com.car_workshop.Car.repository.UserRepository;
import com.car_workshop.Car.security.jwt.JwtToken;

@Service
public class AuthService {

	@Autowired
	private JwtToken tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialDto data) {
		try {
			var userName = data.getUserName();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			
			User user = userRepository.findByUsername(userName);
			
			var tokenResponse = new TokenDto();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());
			} 
			else {
				throw new UsernameNotFoundException("UserName" +userName+  " not Found!");
			}
			
			return ResponseEntity.ok(tokenResponse);
		} 
		catch (Exception e) {
			throw new BadCredentialsException("Erro: " +e.getMessage()+ " - " +e.getStackTrace());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
			User user = userRepository.findByUsername(username);
			
			var tokenResponse = new TokenDto();
			if (user != null) {
				tokenResponse = tokenProvider.refreshToken(refreshToken);
			} 
			else {
				throw new UsernameNotFoundException("UserName" +username+  " not Found!");
			}
			
			return ResponseEntity.ok(tokenResponse);
	}
}
