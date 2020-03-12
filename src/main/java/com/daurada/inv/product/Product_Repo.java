package com.daurada.inv.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daurada.base.EntityRepo;

public interface Product_Repo extends EntityRepo<Product> {
	
	@Query(value = "SELECT * FROM product p WHERE p.code = :code", 
			nativeQuery = true)
	Optional<Product> findByCode(@Param("code") String code);

}
