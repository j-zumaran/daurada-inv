package com.daurada.inv;

import org.springframework.boot.test.context.SpringBootTest;

import com.daurada.BaseTest;
import com.daurada.inv.brand.Brand;

@SpringBootTest
public class BrandController_Test extends BaseTest<Brand> {
	
	public BrandController_Test() {
		super(new Brand());
	}

}
