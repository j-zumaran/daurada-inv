package com.daurada.inv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.daurada.ChildBaseTest;
import com.daurada.base.EntityRepo;
import com.daurada.inv.brand.Brand;
import com.daurada.inv.collection.Collection;
import com.daurada.inv.line.Line;

@SpringBootTest
public class LineController_Test extends ChildBaseTest<Line, Collection>{
	
	@Autowired
	private EntityRepo<Brand> brandRepo;
	
	private final Brand brand;

	public LineController_Test() {
		super(new Line(), new Collection());
		brand = new Brand();
		brand.setName("Test brand!");
	}

	@Override
	protected void saveParent() {
		brandRepo.saveAndFlush(brand);
	//	getParentEntity().setBrand(brand);
		super.saveParent();
	}

	@Override
	protected void setParent(Collection parent) {
		parent.setBrand(brand);
		getTestEntity().setCollection(parent); 
	}

}
