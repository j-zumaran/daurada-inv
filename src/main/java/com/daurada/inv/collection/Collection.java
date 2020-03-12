package com.daurada.inv.collection;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.daurada.base.IconEntity;
import com.daurada.inv.brand.Brand;
import com.daurada.inv.line.Line;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Where(clause = IconEntity.DELETED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Collection extends IconEntity {

	private static final long serialVersionUID = -484986164219913900L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id", nullable=false)
	private Brand brand;
	
	@OneToMany(mappedBy = "collection", fetch=FetchType.LAZY)
	private List<Line> lines;
	
	public Collection() {}
	
	public Long getBrandId() {
		return brand.getId();
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	Brand getBrand() {
		return brand;
	}
	
	List<Line> getLines() {
		return lines;
	}
}

