package com.daurada.inv.line;

import org.springframework.stereotype.Service;

import com.daurada.base.BasicService;

@Service
public class Line_Service extends BasicService<Line> {

	@Override
	protected void update(Line old, Line updated) {
		old.setCollection(updated.getCollection());
		old.setName(updated.getName());
		old.setIconPath(updated.getIconPath());
	}

}
