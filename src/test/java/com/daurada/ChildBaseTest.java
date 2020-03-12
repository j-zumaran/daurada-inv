package com.daurada;

import org.springframework.beans.factory.annotation.Autowired;

import com.daurada.base.BasicEntity;
import com.daurada.base.EntityRepo;

public abstract class ChildBaseTest
		<T extends BasicEntity, Parent extends BasicEntity> extends BaseTest<T>{
	
	@Autowired
	private EntityRepo<Parent> parentRepo;

	private final Parent parentEntity;

	public ChildBaseTest(T testEntity, Parent parentEntity) {
		super(testEntity);
		this.parentEntity = parentEntity;
		this.parentEntity.setName(
				"Test " + parentEntity.getClass().getSimpleName().toLowerCase());
	}
	
	protected abstract void setParent(Parent parent);
	
	protected void saveParent() {
		parentRepo.saveAndFlush(parentEntity);
		setParent(parentEntity);
	}
	
	@Override
	public void insertEntity_then_201() throws Exception {
		saveParent();
		super.insertEntity_then_201();
	}
	
	@Override
	public void updateEntity() throws Exception {
		saveParent();
		super.updateEntity();
	}
	
	@Override
	protected void insertToRepo() {
		saveParent();
		super.insertToRepo();
	}
	
	public Parent getParentEntity() {
		return parentEntity;
	}

}
