package com.daurada.dir;

public interface DIR {
	
	public static final String ROOT = "/";
	
	public static final String ERROR = "/error";
	
	public static final String USER = "/user";
	
	public static final String SIGN_IN = USER + "/signin";
	
	public static final String SIGN_OUT = USER + "/signout";
	
	public static final String SIGN_UP = USER + "/signup";
	
	public static final String HOME = USER + "/home";
	
	public static final String AUTH_ERROR = SIGN_IN + ERROR;
	
	public static final String ADMIN = "/admin";
	
	public static final String CONFIG = ADMIN + "/config";

	public static final String ROLES = ADMIN + "/role";

	public static final String GUEST = "/guest";

}
