package com.daurada.inv.brand;

import org.springframework.stereotype.Service;

import com.daurada.base.BasicService;

@Service
public class Brand_Service extends BasicService<Brand> {

	@Override
	protected void update(Brand old, Brand neww) {
		old.setName(neww.getName());
		old.setIconPath(neww.getIconPath());
	}

}
