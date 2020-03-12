package com.daurada.inv.product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Where;

import com.daurada.base.IconEntity;
import com.daurada.inv.color.Color;
import com.daurada.inv.line.Line;
import com.daurada.inv.size.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Where(clause = IconEntity.DELETED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product extends IconEntity {

	private static final long serialVersionUID = 8430336752745205532L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="line_id", nullable=false)
	private Line line;

	@NotEmpty(message = "Code may not be empty.")
	@Column(length = 55, unique = true, nullable = false)
	private String code;
	
	private String description;
	
	@Column(name = "cost_price")
	private double costPrice;
	
	@Column(name = "sell_price", nullable = false)
	private double sellPrice;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<ProductItem> items;
	
	@Transient
	private List<Size> sizes;
	
	@Transient
	private List<Color> colors;
	
	public Long getLineId() {
		return line.getId();
	}
	
	public void setLine(Line line) {
		this.line = line;
	}
	
	Line getLine() {
		return line;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	
	public double getSellPrice() {
		return sellPrice;
	}
	
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}
	
	List<Size> getSizes() {
		return sizes;
	}
	
	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
	
	List<Color> getColors() {
		return colors;
	}
	
	List<ProductItem> getItems() {
		return items;
	}
	
}

