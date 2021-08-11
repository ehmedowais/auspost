package com.auspost.codingtest.exceptions;

import com.auspost.codingtest.util.RequestCorrelation;
import org.springframework.http.HttpStatus;

public class LocationException extends Exception {
	private String message;
	private HttpStatus status;
	private String corelId;
	
	public LocationException(String message,HttpStatus status){
		super(message);
		this.message=message;
		this.status = status;
		corelId = RequestCorrelation.getId();
	}

	public String getMessage() {
		return message + " corelId => "+ corelId;
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
