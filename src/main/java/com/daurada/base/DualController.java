package com.daurada.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class DualController<T extends BasicEntity, S extends BasicEntity> {

	@Autowired
	private BasicService<T> serviceT;
	
	@Autowired
	private BasicService<S> serviceS;

	@GetMapping("/all")
	public ResponseEntity<List<BasicEntity>> getAll() {
		List<T> tList = serviceT.selectAll();
		List<S> sList = serviceS.selectAll();
		 
		List<BasicEntity> all = new ArrayList<BasicEntity>(tList);
		all.addAll(sList);
		
		return ResponseEntity.ok(all);
	}
}
