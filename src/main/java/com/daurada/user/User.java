package com.daurada.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.daurada.base.BasicEntity;

@Entity
@Where(clause = BasicEntity.DELETED)
public class User extends BasicEntity {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Email can't be empty.")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Password can't be empty.")
	@Column(name = "authentication_string", nullable = false)
	private String password;
	
	@Column(name = "last_login")
	private Timestamp lastLogin;
	
	@ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

	@Transient
	private String passwordConfirm;

	private boolean isExpired = false;

	private boolean isLocked = false;

	private boolean credentialsExpired = false;

	private boolean isEnabled = true;

	//private String token;
	
	public User() {}
	
	public User(String name, String email,
				String password, String passwordConfirm) {
		super.setName(name);
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@org.springframework.data.annotation.Transient
	public String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}
	
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@org.springframework.data.annotation.Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}		

	void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public boolean isExpired() {
		return isExpired ;
	}

	public boolean isLocked() {
		return isLocked ;
	}

	public boolean areCredentialsExpired() {
		return credentialsExpired;
	}

	public boolean isEnabled() {
		return isEnabled ;
	}
/*
	public void seToken(Authentication token) {
		this.token = token.toString();
	}
	
	UsernamePasswordAuthenticationToken getToken() {
		return new UsernamePasswordAuthenticationToken(
				getName(), password, getAuthorities());
	}
*/	
	Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedAuths = new HashSet<>();
		
		for (Role role : roles)
			grantedAuths.add(new SimpleGrantedAuthority(role.getName()));
		
		return grantedAuths;
	}

	public static User recentlyCreated(User user) {
		user.setPassword(user.getPasswordConfirm());
		return user;
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "[Email: " + email 
				+ "| Password: " + password 
				+ "| Password confirm: " + passwordConfirm + "]";
	}
}
