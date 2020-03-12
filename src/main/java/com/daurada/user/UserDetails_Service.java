package com.daurada.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetails_Service implements UserDetailsService {
	
    @Autowired
    private User_Repo userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		return loadUserByEmail(username);
	}
	
	@Transactional(readOnly = true)
	public User_Auth loadUserByEmail(String email) 
			throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepository.findByEmail(email);
		
		if (userOpt.isPresent()) 
			return new User_Auth(userOpt.get());
		
		else throw new UsernameNotFoundException(email);
	}
}
