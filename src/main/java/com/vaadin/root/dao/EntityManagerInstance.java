package com.vaadin.root.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;

public class EntityManagerInstance {

    public static EntityManager emInstance;
	
	public EntityManagerInstance(){
		
	}
    
    public static EntityManager getEntityManager() {
    	
        if (emInstance == null ) {
        	
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("application-unit");
        	System.out.println("hello jello");
            EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("application-unit");
       		emInstance = emf.createEntityManager();
        }
        return emInstance;
    }

}
