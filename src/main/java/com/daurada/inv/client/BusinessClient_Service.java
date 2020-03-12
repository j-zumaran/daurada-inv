package com.daurada.inv.client;

import org.springframework.stereotype.Service;

import com.daurada.base.BasicService;

@Service
public class BusinessClient_Service extends BasicService<BusinessClient> {

	@Override
	protected void update(BusinessClient old, BusinessClient updated) {
		old.setName(updated.getName());
		old.setEmail(updated.getEmail());
		old.setIdNumber(updated.getIdNumber());
		old.setPhone(updated.getPhone());
		old.setAddress(updated.getAddress());
		old.setLocation(updated.getLocation());
	}

}
