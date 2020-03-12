package com.daurada.inv.size;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Where;

import com.daurada.base.BasicEntity;

@Entity
@Where(clause = BasicEntity.DELETED)
public class Size extends BasicEntity {

	private static final long serialVersionUID = -5254346325983934205L;
	
	@Column(length = 55)
	private String dimension;
	
	public String getDimension() {
		return dimension;
	}
	
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
}
