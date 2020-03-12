package com.daurada.inv;

import org.springframework.boot.test.context.SpringBootTest;

import com.daurada.ChildBaseTest;
import com.daurada.inv.brand.Brand;
import com.daurada.inv.collection.Collection;

@SpringBootTest
public class CollectionController_Test extends ChildBaseTest<Collection, Brand> {

	public CollectionController_Test() {
		super(new Collection(), new Brand());
	}

	@Override
	protected void setParent(Brand parent) {
		getTestEntity().setBrand(parent);
	}

}
