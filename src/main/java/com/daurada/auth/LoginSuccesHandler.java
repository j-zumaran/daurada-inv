package com.daurada.auth;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.daurada.dir.DIR;

@Component
public class LoginSuccesHandler implements AuthenticationSuccessHandler {
  
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
    		HttpServletResponse response, Authentication authentication) throws IOException {
  
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request,  HttpServletResponse response,
    		Authentication authentication) throws IOException {
  
        if (!response.isCommitted()) 
            redirectStrategy.sendRedirect(
            		request, response, redirectTo(authentication));
    }
 
    protected String redirectTo(Authentication auth) {
    	
        List<String> authorities = auth.getAuthorities().stream()
        		.map(a -> a.getAuthority())
        		.collect(Collectors.toList());
        
        if (authorities.stream().anyMatch(
        		a -> a.equals(Authority.GUEST.name())))
        	return DIR.GUEST;
        
        if (authorities.stream().anyMatch(
        		a -> a.equals(Authority.USER.name())))
        	return DIR.HOME;
        
        if (authorities.stream().anyMatch(
        		a -> a.equals(Authority.ADMIN.name())))
        	return DIR.HOME;
 
        throw new IllegalStateException();
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
