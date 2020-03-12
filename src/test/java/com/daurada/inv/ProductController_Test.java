package com.daurada.inv;

import org.springframework.boot.test.context.SpringBootTest;

import com.daurada.BaseTest;
import com.daurada.inv.product.Product;

@SpringBootTest
public class ProductController_Test extends BaseTest<Product> {

	public ProductController_Test() {
		super(test());
		// TODO Auto-generated constructor stub
	}
	
	private static Product test() {
		Product prod = new Product();
		prod.setCode("TESTCODE");
		prod.setDescription("Some description");
		prod.setCostPrice(10);
		prod.setSellPrice(20);
		//prod.
		return prod;
	}

}
