package com.daurada.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daurada.base.EntityRepo;

public interface User_Repo extends EntityRepo<User> {
	
	@Query(value = "SELECT * FROM user p WHERE p.email = :email", nativeQuery = true)
	Optional<User> findByEmail(@Param("email") String email);
/*
	@Query(value = "UPDATE user p SET p.email = :email", nativeQuery = true)
	@Where(clause = "")
	void updateLastLogin(Timestamp timestamp);
*/	
}
