package com.car_workshop.Car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	
	public InvalidJwtException(String str) {
		super(str);
	}
}
