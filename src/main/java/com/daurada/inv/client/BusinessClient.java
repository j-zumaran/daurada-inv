package com.daurada.inv.client;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.daurada.base.BasicEntity;
import com.daurada.base.RegisteredEntity;
import com.daurada.inv.location.Location;

@Entity
@Table(name = "client_business")
@Where(clause = BasicEntity.DELETED)
public class BusinessClient extends RegisteredEntity {

	private static final long serialVersionUID = -219199860200694837L;
	
	@Override
	protected Location getLocation() {
		return super.getLocation();
	}

}
