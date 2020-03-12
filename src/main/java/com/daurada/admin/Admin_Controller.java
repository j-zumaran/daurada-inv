package com.daurada.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.dir.DIR;

@RestController
public class Admin_Controller {
	
	@GetMapping(DIR.CONFIG)
	public String config(Model model) {
		return "Configuration";
	}
	
	@GetMapping(DIR.ADMIN)
	public String admin(Model model) {
		return "Admin";
	}

}
