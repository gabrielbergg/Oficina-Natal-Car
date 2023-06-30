package com.car_workshop.Car.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResponseException implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime data;
	private String mensage;
	private String datails;
	
	public ResponseException() {
	}

	public ResponseException(LocalDateTime data, String mensage, String datails) {
		this.data = data;
		this.mensage = mensage;
		this.datails = datails;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	public String getDatails() {
		return datails;
	}

	public void setDatails(String datails) {
		this.datails = datails;
	}
}