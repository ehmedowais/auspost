package com.auspost.codingtest.exceptions;

import org.springframework.http.HttpStatus;

public class LocationException extends Exception {
	private String message;
	private HttpStatus status;
	
	public LocationException(String message,HttpStatus status){
		super(message);
		this.message=message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
