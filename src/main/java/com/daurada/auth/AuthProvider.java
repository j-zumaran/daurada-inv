package com.daurada.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.daurada.user.UserDetails_Service;
import com.daurada.user.User_Auth;

@Component
public class AuthProvider implements AuthenticationProvider {
	
    @Autowired
    private UserDetails_Service userService;
    
    @Autowired
    private BCryptPasswordEncoder crypto;

	@Override
	public Authentication authenticate(Authentication attempt) throws AuthenticationException {
		
		if (attempt == null) 
			throw new AuthenticationCredentialsNotFoundException("Authentication is empty.");
		
		if (attempt.getPrincipal() == null) 
			throw new AuthenticationCredentialsNotFoundException("Missing email!");
		
		if (attempt.getCredentials() == null) 
			throw new AuthenticationCredentialsNotFoundException("Missing credentials!");
		
		UsernamePasswordAuthenticationToken auth = 
				generateToken(userService.loadUserByEmail(attempt.getName()));
		
		if (crypto.matches(
				attempt.getCredentials().toString(), 
				auth.getCredentials().toString())) 
			return auth;
		
		throw new AuthenticationCredentialsNotFoundException("Failed authentication.");
	}
/*	
	boolean authenticate(User_Auth user) {
		LOG.LOGGER.debug("autehnticating... " + user.toString());
    	Authentication auth = authenticate(generateToken(user));
    	
    	if (auth != null) {
        	user.setToken(auth);
    		return auth.isAuthenticated();
    	}
    	return false;
	}
*/	
	static UsernamePasswordAuthenticationToken generateToken(User_Auth user) {
		return new UsernamePasswordAuthenticationToken(
    			user.getEmail(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
