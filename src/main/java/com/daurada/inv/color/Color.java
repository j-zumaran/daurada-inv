package com.daurada.inv.color;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Where;

import com.daurada.base.BasicEntity;

@Entity
@Where(clause = BasicEntity.DELETED)
public class Color extends BasicEntity {

	private static final long serialVersionUID = 5942243787091885098L;

	@Column(length = 12)
	private String rgb;
	
	public String getRgb() {
		return rgb;
	}
	
	public void setRgb(String rgb) {
		this.rgb = rgb;
	}
}
