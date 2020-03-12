package com.daurada.inv.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.base.BasicController;
import com.daurada.http.HttpResponse;

@RestController
@RequestMapping("/product")
public class Product_Controller extends BasicController<Product> {
	
	@Autowired
	private ProductItem_Service itemService;
	
	@Override
	public ResponseEntity<?> insert(@RequestBody Product entity) {
		if (entity.getSizes() == null || entity.getColors() == null) 
			return HttpResponse.bad("Sizes and colors must not be null.", entity);
		
		if (entity.getSizes().isEmpty() || entity.getColors().isEmpty()) 
			return HttpResponse.bad("Sizes and colors must not be empty.", entity);
		
		return super.insert(entity);
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<Product> findByCode(@PathVariable String code) {
		Optional<Product> prod = ((Product_Service) getService()).findByCode(code);
		
		if (prod.isPresent()) 
			return ResponseEntity.ok(prod.get());
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/items")
	public ResponseEntity<List<ProductItem>> items() {
		return ResponseEntity.ok(itemService.selectAll());
	}

}
