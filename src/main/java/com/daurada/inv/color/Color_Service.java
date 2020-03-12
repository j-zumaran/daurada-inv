package com.daurada.inv.color;

import org.springframework.stereotype.Service;

import com.daurada.base.BasicService;

@Service
public class Color_Service extends BasicService<Color> {

	@Override
	protected void update(Color old, Color updated) {
		old.setName(updated.getName());
		old.setRgb(updated.getRgb());
	}

}
