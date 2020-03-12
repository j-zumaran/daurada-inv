package com.daurada.inv.size;

import org.springframework.stereotype.Service;

import com.daurada.base.BasicService;

@Service
public class Size_Service extends BasicService<Size> {

	@Override
	protected void update(Size old, Size updated) {
		old.setName(updated.getName());
		old.setDimension(updated.getDimension());
	}

}
