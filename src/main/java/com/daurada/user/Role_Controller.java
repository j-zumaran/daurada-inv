package com.daurada.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.base.BasicController;
import com.daurada.dir.DIR;

@RestController
@RequestMapping(DIR.ROLES)
public class Role_Controller extends BasicController<Role> {

}
