package com.daurada.dir;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Directory_Controller {
	
	@GetMapping(DIR.ROOT)
    public String root(Model model) {
        return "Root";
    }

}
