package com.daurada.user;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class User_Validator implements Validator {
	
    @Autowired
    private User_Service userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty",
				"Field name can't be empty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty",
				"Field email can't be empty");
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty",
				"Field password can't be empty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty",
				"Field passwordConfirm can't be empty");
		
        if (user.getName().length() < 6 || user.getName().length() > 255) 
            errors.rejectValue("name", "User.name.size", 
            		"User name must be between 6 and 255 characters.");
        
        if (!validateEmail(user.getEmail()))
        	errors.rejectValue("email", "User.email.format", 
            		"Invalid Email format!");
        
        if (userService.findByEmail(user.getEmail()).isPresent()) 
            errors.rejectValue("email", "Email.duplicate",
            		"There's other user registered with this email.");
        
        if (user.getPassword() != null && user.getPasswordConfirm() != null) {
        	if (user.getPassword().length() < 8) 
        		errors.rejectValue("password", "User.password.size",
        				"Password must be at least 8 characters long.");
        
        	if (!user.getPasswordConfirm().equals(user.getPassword())) 
        		errors.rejectValue("passwordConfirm", "User.password.diff",
        				"Passwords do not match");
        }
	}
	
	boolean validateEmail(String email) {
		if (email == null) return false;
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(email).matches();
	}
}

