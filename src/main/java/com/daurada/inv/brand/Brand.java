package com.daurada.inv.brand;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.daurada.base.IconEntity;
import com.daurada.inv.collection.Collection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Where(clause = IconEntity.DELETED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Brand extends IconEntity {

	private static final long serialVersionUID = -7744688263117354598L;
	
	@OneToMany(mappedBy="brand", fetch=FetchType.LAZY)
    private List<Collection> collections;
	
	List<Collection> getCollections() {
		return collections;
	}
}
