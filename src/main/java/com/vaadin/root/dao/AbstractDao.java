package com.vaadin.root.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

public abstract class AbstractDao< T extends Serializable > {
	 
	   private Class< T > clazz;
	   
	   private EntityManagerFactory emf = Persistence.createEntityManagerFactory("application-unit");
	   
	   private EntityManager entityManager = emf.createEntityManager();
	   
	   
	 
//	   public AbstractDao(Class<T> clazz, EntityManagerFactory emf, EntityManager entityManager) {
	   public AbstractDao(Class<T> clazz, EntityManager entityManager) {
		super();
		this.clazz = clazz;
//		this.emf = emf;
		this.entityManager = entityManager;
	}

	public final void setClazz( Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	   }
	 
	   public T findOne( long id ){
	      return entityManager.find( clazz, id );
	   }
	   public List< T > findAll(){
		   System.out.println("class==>"+clazz.getName());
//	      return entityManager.createQuery( "from " + clazz.getName() )
		   
		   System.out.println("number of rows==>"+ entityManager.createQuery( "Select d from " + clazz.getName() +" d" ).getMaxResults());
	      
	      return entityManager.createQuery( "Select d from " + clazz.getName() +" d" )
	       .getResultList();
	   }
	 
	   public void create( T entity ){
	      entityManager.persist( entity );
	   }
	 
	   public T update( T entity ){
	      return entityManager.merge( entity );
	   }
	 
	   public void delete( T entity ){
	      entityManager.remove( entity );
	   }
	   public void deleteById( long entityId ){
	      T entity = findOne( entityId );
	      delete( entity );
	   }
	}