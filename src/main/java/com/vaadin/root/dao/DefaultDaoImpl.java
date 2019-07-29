package com.vaadin.root.dao;

import javax.persistence.EntityManager;

public class DefaultDaoImpl implements DefaultDao_ {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityManager getEntityManager() {
		return new EntityManagerInstance().getEntityManager();
	}
	
	

}
