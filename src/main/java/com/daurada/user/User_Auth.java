package com.daurada.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User_Auth implements UserDetails {

	private static final long serialVersionUID = 4578183097853292512L;

	private final User user;

	public User_Auth(User user) {
		this.user = user;
	}
	
	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return  user.areCredentialsExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
/*
	public void setToken(Authentication token) {
		user.seToken(token);
	}
	
	public UsernamePasswordAuthenticationToken getToken() {
		return user.getToken();
	}
*/
}
