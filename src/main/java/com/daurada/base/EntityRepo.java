package com.daurada.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepo<T extends BasicEntity> extends JpaRepository<T, Long> {
	
}
