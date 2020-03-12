package com.daurada.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daurada.user.User;
import com.daurada.user.User_Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class Security_Service {
	
    @Autowired
    private AuthenticationManager authManager;

    private static final Logger logger = LoggerFactory.getLogger(Security_Service.class);

    String findLoggedInUsername() {
        Object user = SecurityContextHolder.getContext()
        				.getAuthentication().getDetails();
        
        if (user instanceof User) 
        	return ((User) user).getName();
        return null;
    }
    
    @Transactional(readOnly = true)
    public void login(User user) {
    	
    	UsernamePasswordAuthenticationToken token = 
    			AuthProvider.generateToken(new User_Auth(user));
    	
    	if (authManager.authenticate(token) != null) {
            logger.debug(String.format("Auto login %s successfully!", user.getEmail()
            		+ " " + findLoggedInUsername()));
    		SecurityContextHolder.getContext().setAuthentication(token);
    	}
    }

    @Transactional(readOnly = true)
    public void autoLogin(User user) {
    	login(user);
    }
}
