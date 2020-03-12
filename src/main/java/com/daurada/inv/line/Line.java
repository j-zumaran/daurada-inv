package com.daurada.inv.line;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.daurada.base.IconEntity;
import com.daurada.inv.collection.Collection;
import com.daurada.inv.product.Product;

@Entity
@Where(clause = IconEntity.DELETED)
public class Line extends IconEntity {

	private static final long serialVersionUID = -8989463200054428258L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_id", nullable=false)
	private Collection collection;
	
	@OneToMany(mappedBy = "line")
	private List<Product> products;
	
	public Line() {}

	public Long getCollectionId() {
		return collection.getId();
	}
	
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	
	Collection getCollection() {
		return collection;
	}
	
	List<Product> getProducts() {
		return products;
	}
}


