package com.daurada.http;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.dir.DIR;

@RestController
public class ErrController implements ErrorController {
    
    @RequestMapping(DIR.ERROR)
    public ResponseEntity<Object> handleError(HttpServletRequest request) {
    	
    	int statusCode = (int) request.getAttribute(
    			RequestDispatcher.ERROR_STATUS_CODE);
    	
    	HttpStatus status = HttpStatus.valueOf(statusCode);
    	
    	Exception exception = (Exception) request.getAttribute(
    			RequestDispatcher.ERROR_EXCEPTION);
    	
    	return ErrorHandler.handleAll(exception, status);
    }
    
	@Override
	public String getErrorPath() {
		return DIR.ERROR;
	}

}
