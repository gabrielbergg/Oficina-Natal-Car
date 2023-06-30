package com.car_workshop.Car.exceptions.handler;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.car_workshop.Car.exceptions.InvalidJwtException;
import com.car_workshop.Car.exceptions.ResouceNotFoundException;
import com.car_workshop.Car.exceptions.ResponseException;

@ControllerAdvice
@RestController
public class CustumizerResponseEntityHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseException> handler(Exception ex, WebRequest request) {
		ResponseException exceptionResponse = new ResponseException(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResouceNotFoundException.class)
	public final ResponseEntity<ResponseException> handlerBadRequest(Exception ex, WebRequest request) {
		ResponseException exceptionResponse = new ResponseException(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidJwtException.class)
	public final ResponseEntity<ResponseException> handlerInvalidJwt(Exception ex, WebRequest request) {
		ResponseException exceptionResponse = new ResponseException(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}
}
