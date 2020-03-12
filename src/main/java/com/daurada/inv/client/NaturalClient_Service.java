package com.daurada.inv.client;

import org.springframework.stereotype.Service;

import com.daurada.base.BasicService;

@Service
public class NaturalClient_Service extends BasicService<NaturalClient> {

	@Override
	protected void update(NaturalClient old, NaturalClient updated) {
		old.setName(updated.getName());
		old.setEmail(updated.getEmail());
		old.setIdNumber(updated.getIdNumber());
		old.setPhone(updated.getPhone());
		old.setAddress(updated.getAddress());
		old.setLocation(updated.getLocation());
	}

}
