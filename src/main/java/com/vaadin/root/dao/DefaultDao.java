package com.vaadin.root.dao;

import javax.persistence.EntityManager;

public class DefaultDao {
	
	public EntityManager getEntityManager() {
		return new EntityManagerInstance().getEntityManager();
	}

}
