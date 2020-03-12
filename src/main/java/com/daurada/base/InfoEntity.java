package com.daurada.base;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import com.daurada.inv.location.Location;

@MappedSuperclass
public class InfoEntity extends BasicEntity {

	private static final long serialVersionUID = -3130148486522736379L;
	
	@Column(length = 255)
	private String email;
	
	@Column(length = 255)
	private String phone;
	
	@Column(length = 255)
	private String address;

	@NotEmpty(message = "City may not be null.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false)
	private Location location;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getLocationId() {
		return location.getId();
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	protected Location getLocation() {
		return location;
	}
	
	

}
