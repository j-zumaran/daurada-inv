package com.daurada.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daurada.base.EntityRepo;

public interface Role_Repo extends EntityRepo<Role> {

	@Query(value = "SELECT * FROM role p WHERE p.name = :name", nativeQuery = true)
	Optional<Role> findByName(@Param("name") String name);
}
