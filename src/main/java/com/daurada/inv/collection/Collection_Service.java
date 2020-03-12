package com.daurada.inv.collection;

import org.springframework.stereotype.Service;

import com.daurada.base.BasicService;

@Service
public class Collection_Service extends BasicService<Collection>{

	@Override
	protected void update(Collection old, Collection updated) {
		old.setBrand(updated.getBrand());
		old.setName(updated.getName());
		old.setIconPath(updated.getIconPath());
	}

}
