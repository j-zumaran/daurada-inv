package com.daurada.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.http.HttpResponse;
import com.daurada.http.ResponseObject;

@RestController
public abstract class BasicController<T extends BasicEntity> {
	
	@Autowired
	private BasicService<T> service;
	
	private final String type = 
			StringUtils.getFilenameExtension(
					((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0].getTypeName());
			

	@GetMapping("/all")
	public ResponseEntity<List<T>> getAll() {
		return ResponseEntity.ok(service.selectAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable long id) {
		Optional<T> result = service.selectById(id);
		if (result.isPresent())
			return ResponseEntity.ok(result.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody T entity) {	
		if (service.insert(entity)) return HttpResponse.created(entity);		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseObject> update(
			@PathVariable Long id, @RequestBody T entity) {
		
		if (service.update(id, entity)) return HttpResponse.updated(entity);
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable long id) {
		if (service.delete(id)) return HttpResponse.deleted(type, id);
		return ResponseEntity.notFound().build();
	}
	
	protected BasicService<T> getService() {
		return service;
	}
	
	public String getType() {
		return type.toLowerCase();
	}
	
	public String getAddPath() {
		return "/" + getType() + "/add";
	}

	public String getAllPath() {
		return "/" + getType() + "/all";
	}
	
	public String getUpdatePath(long id) {
		return "/" + getType() + "/update/" + id;
	}

	public String getDeletePath(long id) {
		return "/" + getType() + "/delete/" + id;
	}

	public String getIdPath(long id) {
		return "/" + getType() + "/" + id;
	}
}
