package com.car_workshop.Car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResouceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResouceNotFoundException(String srt) {
		super(srt);
	}
}