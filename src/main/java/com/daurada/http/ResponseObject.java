package com.daurada.http;

public class ResponseObject {
	
	private final Object message;
	
	private final Object object;

	public ResponseObject(Object message, Object detail) {
		this.message = message;
		this.object = detail;
	}
	
	public Object getMessage() {
		return message;
	}
	
	public Object getObject() {
		return object;
	}
	
	@Override
	public String toString() {
		return "[Message: " + message + "| Detail: " + object + "]";
	}
}
