package com.daurada.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public abstract class RegisteredEntity extends InfoEntity {

	private static final long serialVersionUID = 7814952993835267011L;
	
	@NotEmpty(message = "ID number must not be null.")
	@Column(name = "id_number", length = 11, nullable = false, unique = true)
	private String idNumber;
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}
