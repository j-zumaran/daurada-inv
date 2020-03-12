package com.daurada.inv.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.daurada.base.BasicEntity;
import com.daurada.base.IconEntity;
import com.daurada.inv.color.Color;
import com.daurada.inv.size.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Where(clause = IconEntity.DELETED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductItem extends BasicEntity {

	private static final long serialVersionUID = -2023546127106629531L;
	
	@NotNull(message = "Product id must not be null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@NotNull(message = "Size id must not be null")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="size_id", nullable=false)
	private Size size;
	
	@NotNull(message = "Color id must not be null")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="color_id", nullable=false)
	private Color color;
	
	@NotEmpty(message = "Barcode must not be null")
	@Column(nullable = false, unique = true)
	private String barcode;
	
	@Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private int stock = 0;

	public Long getProductId() {
		return product.getId();
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	Product getProduct() {
		return product;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public int getStock() {
		return stock;
	}


}
