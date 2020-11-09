package com.vaadin.root.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.io.Serializable;
import java.util.logging.Logger; 

public class DefaultDao implements Dao, Serializable {
	
	private static final long serialVersionUID = -820996462757623759L;
	
	private static final Logger logger = Logger.getGlobal();

	public EntityManager getEntityManager() {
		return new EntityManagerInstance().getEntityManager();
	}

	public EntityManager getDaoEntityManager() {
		EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("application-unit");
		return emf.createEntityManager();
	}
	
	
	/**
	 *
	 * Update or create entity of passed in type. If id is null, the entity is
	 * created, otherwise, it is updated.
	 *
	 * This method does not refresh the entity after persisting/merging. If you need
	 * this, you should do it after committing the transaction.
	 *
	 * Use this method if you already started a transaction and need this operation
	 * to execute as part of it. A new transaction will not be started when you call
	 * this method. It assumes one has already been created before calling this
	 * method.
	 */
	public <T, PK> T updateOrCreateEntity(T entity, PK id, EntityManager em) throws RuntimeException {
		if (em == null) {
			throw new IllegalArgumentException(
					"You must pass in a valid entity manager.  Use the other updateOrCreateEntity method if you want to start a new transaction.");
		}

		try {
			if (id == null) {
				em.persist(entity);
			} else {
				entity = em.merge(entity);
			}

			return entity;
		} catch (RuntimeException e) {

			String errorLogMsg = "Error saving entity class: " + entity.getClass();
			if (id != null) {
				errorLogMsg = errorLogMsg + " with id: " + id;
			}
			logger.fine(errorLogMsg+e);
			
			throw e;
		}

	}
	
	public <T, PK> T updateOrCreateEntity(T entity, PK id) {
		EntityManager em = null;
		try {
			em = getDaoEntityManager();

			if(em.isOpen())
				logger.fine("em is open");
			else
				logger.fine("em is not open");
				
			em.getTransaction().begin();
			T result = updateOrCreateEntity(entity, id, em);
			em.getTransaction().commit();

			// refresh entity after save to update all children.
			em.refresh(result);

			return result;
		} catch (RuntimeException e) {
			if (em != null && em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			// log error
			String errorLogMsg = "Error saving entity class: " + entity.getClass();
			if (id != null) {
				errorLogMsg = errorLogMsg + " with id: " + id;
			}
			logger.fine(errorLogMsg+e);

			throw e;
		} finally {
			logger.fine("hello jello");
//			if (em != null) {
			if (em != null && em.isOpen()) {

			if(em.isOpen())
				logger.fine("em is open");
			else
				logger.fine("em is not open");
				
				logger.fine("em is open????==>"+em.isOpen());
				em.close();
			}
		}
	}


}
