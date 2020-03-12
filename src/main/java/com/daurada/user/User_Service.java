package com.daurada.user;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daurada.auth.Authority;
import com.daurada.base.BasicService;

@Service
public class User_Service extends BasicService<User> {
	
	@Autowired
    private BCryptPasswordEncoder crypto;
	
	@Autowired
	private Role_Repo roleRepo;
	
	@Override
	@Transactional
	public boolean insert(User user) {
		user.setPassword(crypto.encode(user.getPassword()));
		
		Optional<Role> roleOpt = roleRepo.findByName(Authority.USER.name());
		if (roleOpt.isPresent())
			user.setRoles(new HashSet<Role>(
					Collections.singleton(roleOpt.get())));
		
		return super.insert(user);
	}
	
	@Transactional
	public void updateLastLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
			findByEmail(auth.getPrincipal().toString()).ifPresent(user -> {
				user.setLastLogin(new Timestamp(System.currentTimeMillis()));
				getRepo().flush();
			});
		else throw new AuthenticationCredentialsNotFoundException(
				"Current user not authenticated. Last login not updated.");
	}
	
	@Transactional
	public Optional<User> findByEmail(String email) {
		return ((User_Repo) getRepo()).findByEmail(email);
	}

	@Override
	protected void update(User old, User updated) {
		return;
	}
	
	@Override
	public List<User> selectAll() {
		return Collections.emptyList();
	}

}
