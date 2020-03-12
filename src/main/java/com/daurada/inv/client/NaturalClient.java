package com.daurada.inv.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.daurada.base.BasicEntity;
import com.daurada.base.RegisteredEntity;
import com.daurada.inv.location.Location;

@Entity
@Table(name = "client_natural")
@Where(clause = BasicEntity.DELETED)
public class NaturalClient extends RegisteredEntity {

	private static final long serialVersionUID = -2354605039428270561L;
	
	@Column(precision = 2)
	private double reputation;
	
	public double getReputation() {
		return reputation;
	}
	
	public void setReputation(double reputation) {
		this.reputation = reputation;
	}
	
	@Override
	protected Location getLocation() {
		return super.getLocation();
	}
}
