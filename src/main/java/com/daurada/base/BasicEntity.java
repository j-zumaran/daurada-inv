package com.daurada.base;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class BasicEntity implements Serializable {

	private static final long serialVersionUID = 3778493562011050616L;
	
	public static final String DELETED = "deleted = 'FALSE'";

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name may not be empty.")
	@Size(min = 1, message = "Name must be at least 1 character long.") 
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private boolean deleted = false;
	
	public BasicEntity() {}
	
	public BasicEntity(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Transient
	public boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public String toString() {
		return "[ID: " + id + "| Name: " + name + "]";
	}
}
