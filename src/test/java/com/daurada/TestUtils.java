package com.daurada;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.daurada.base.LOG;
import com.daurada.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	
	static final String EDITED = "Test edited name!";

	public static String toJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static String toFormUrlEncoded(final Object obj) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		StringBuilder form = new StringBuilder();
		for (Method m: obj.getClass().getMethods()) {
			
			if (m.getName().startsWith("get")) {
				Object resultVal = m.invoke(obj);
				
				if (resultVal instanceof String
					|| resultVal instanceof Number) {
					
					form.append(m.getName()
							.replace("get", "").toLowerCase());
					form.append("=" + resultVal + "&");
				}
			}
		}
		form.deleteCharAt(form.length() - 1);
		LOG.LOGGER.warn(form.toString());
		return form.toString();
	}
	public static String toFormUrlEncoded(final User user) {
		StringBuilder form = new StringBuilder();
		form.append("username=" + user.getEmail());
		form.append("&password=" + user.getPassword());
		form.append("&passwordConfirm=" + user.getPasswordConfirm());
		return form.toString();
	}
}
