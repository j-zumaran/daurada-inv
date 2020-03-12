package com.daurada.inv.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daurada.base.BasicService;

@Service
public class Product_Service extends BasicService<Product> {
	
	@Autowired
	private ProductItem_Service itemService;
	
	@Override
	public boolean insert(Product entity) {
		if (super.insert(entity)) {
			itemService.insertAll(entity);
			return true;
		}
		return false;
	}
	
	@Transactional
	public Optional<Product> findByCode(String code) {
		return ((Product_Repo) getRepo()).findByCode(code);
	}

	@Override
	protected void update(Product old, Product updated) {
		old.setLine(updated.getLine());
		old.setName(updated.getName());
		old.setCode(updated.getCode());
		old.setIconPath(updated.getIconPath());
		old.setDescription(updated.getDescription());
		old.setCostPrice(updated.getCostPrice());
		old.setSellPrice(updated.getSellPrice());
	}

}
