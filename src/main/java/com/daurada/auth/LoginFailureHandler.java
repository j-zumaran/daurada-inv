package com.daurada.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.daurada.dir.DIR;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, 
			HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		response.addHeader("Error", "Authentication error: " + exception.getLocalizedMessage());
		redirectStrategy.sendRedirect(request, response, DIR.AUTH_ERROR);
	}

}
