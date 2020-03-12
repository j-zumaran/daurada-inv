package com.daurada.http;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class HttpResponse {
	
	public static ResponseEntity<ResponseObject> created(Object object) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).body(
				new ResponseObject("Added to database", object));
	}
	
	public static ResponseEntity<ResponseObject> updated(Object object) {
		
		return ResponseEntity.accepted().body(
				new ResponseObject("Updated", object));
	}

	public static<T> ResponseEntity<ResponseObject> deleted(String type, long id) {
		return ResponseEntity.accepted().body(
				new ResponseObject("Deleted", type + ": " + id));
	}
	
	public static<T> ResponseEntity<ResponseError> bad(String msg, Object object) {
		return ResponseEntity.badRequest().body(
				new ResponseError(HttpStatus.BAD_REQUEST, msg, object));
	}

}
