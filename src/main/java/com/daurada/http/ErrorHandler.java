package com.daurada.http;

import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
    		Exception ex, Object body, HttpHeaders headers,
    		HttpStatus status, WebRequest request) {
		
    	logger.error(ex.getLocalizedMessage());
    	
        return ResponseEntity.badRequest().body(
        		new ResponseError(status,
        		ex.getClass().getSimpleName(),
        		ex.getLocalizedMessage()));
    }
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		logger.error(ex.getLocalizedMessage());

        return ResponseEntity.badRequest().body(
        		new ResponseError(status,
        		"Parameter '" + ex.getValue() + "' must be of type " 
        		+ ex.getRequiredType().getName(),
        		ex.getLocalizedMessage()));
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		logger.warn(ex.getLocalizedMessage());
    	
        return ResponseEntity.status(status).body(
        		new ResponseError(status, null,
        		ex.getLocalizedMessage()));
	}
	
	public static ResponseEntity<Object> handleAll(Exception ex, HttpStatus status) {
		ResponseError error = logError(ex, status);
		return ResponseEntity.status(error.getStatus()).body(error);
	}
	
	public static ResponseError logError(Exception e, HttpStatus status) {
		
		StringBuilder error = new StringBuilder(status.getReasonPhrase() + " ");
		
    	if (e != null) {
    		Throwable root = rootCause(e);
    		error.append(root.getLocalizedMessage());
    		
    		if(root instanceof ConstraintViolationException) 
    			return handleConstraintViolation(root, error);
    		
    		if(root instanceof UnexpectedRollbackException) 
    			return handleUnexpectedRollback(root, error);
    	} 
    	logger.error(error.toString());
    	
    	return new ResponseError(status, error.toString(), null);
	}
	
	private static ResponseError handleConstraintViolation(
			Throwable exception, final StringBuilder error) {
		
		Stream<ConstraintViolation<?>> constraints = 
				((ConstraintViolationException) exception)
					.getConstraintViolations().stream();
		
		StringBuilder violations = new StringBuilder();
		
		constraints.forEach(c -> {
			violations.append(c.getRootBeanClass().getSimpleName() + ": ");
			violations.append(c.getMessageTemplate() + " ");
		});
		
		logger.error(violations.toString() + "\n" + error.toString());
		return new ResponseError(HttpStatus.BAD_REQUEST, error.toString(), violations);
	}
	
    private static ResponseError handleUnexpectedRollback(Throwable ex, StringBuilder error) {
    	String msg = UnexpectedRollbackException.class.cast(ex)
    					.getMostSpecificCause().getLocalizedMessage();
    	logger.error(msg + "\n" + error);
    	return new ResponseError(HttpStatus.BAD_REQUEST, msg, error);
	}

	private static Throwable rootCause(Throwable ex) {
    	Throwable cause = ex.getCause();
    	if (cause == null) 
    		return ex;
    	return rootCause(cause);
    }

	public static void handleException(Exception e) {
		logger.error(e.getLocalizedMessage() + " " + e.getCause());
	}

}