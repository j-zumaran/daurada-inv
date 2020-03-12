package com.daurada.http;

import org.springframework.http.HttpStatus;

public class ResponseError {
	
	private HttpStatus status;
	
	private String message;
	
	private Object error;

	public ResponseError(HttpStatus status, String message, Object error) {
		this.status = status;
		this.message = message == null? "No message available.": message;
		this.error = error == null? "No error available.": error;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Object getError() {
		return error;
	}
}
