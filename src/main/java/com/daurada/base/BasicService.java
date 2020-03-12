package com.daurada.base;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daurada.http.ErrorHandler;

@Service
public abstract class BasicService<T extends BasicEntity> {
	
	@Autowired
	private EntityRepo<T> repo;
	
	@Transactional
	public List<T> selectAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Optional<T> selectById(long id) {
		return repo.findById(id);
	}
	
	@Transactional
	public List<T> insertAll(Iterable<T> entities) {
		return repo.saveAll(entities);
	}

	@Transactional
	public boolean insert(T entity) {
		try {
			repo.saveAndFlush(entity);
			return true;
		} catch (Exception e) {
			ErrorHandler.handleException(e);
			return false;
		}
	}

	@Transactional
	public boolean update(Long id, T updated) {
		try {
			Optional<T> old = repo.findById(id);
			
			if (old.isPresent()) {
				update(old.get(), updated);
				repo.flush();
				return true;
			}
		} catch (ConstraintViolationException e) {
			ErrorHandler.handleException(e);
		}
		return false;
	}
	
	protected abstract void update(T old, T updated);
	
	@Transactional
	public boolean delete(Long id) {
		try {
			Optional<T> entity = repo.findById(id);
			
			if (entity.isPresent()) {
				entity.get().setDeleted(true);
				repo.flush();
				return true;
			}
		} catch (ConstraintViolationException e) {
			ErrorHandler.handleException(e);
		}
		return false;
	}

	@Transactional
	public boolean purge(Long id) {
		try {
			Optional<T> entity = repo.findById(id);
			
			if (entity.isPresent()) {
				if (entity.get().isDeleted()) {
					repo.delete(entity.get());
					return true;
				}
			}
		} catch (ConstraintViolationException e) {
			ErrorHandler.handleException(e);
		}
		return false;
	}
	
	protected EntityRepo<T> getRepo() {
		return repo;
	}
}
