package com.daurada.inv.brand;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daurada.base.BasicController;

@RestController
@RequestMapping("/brand")
public class Brand_Controller extends BasicController<Brand> {}
