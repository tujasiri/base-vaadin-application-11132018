package com.vaadin.root.dao;

import javax.persistence.EntityManager;

public interface Dao {
	
	/**
	 * Get the entitymanager used for the session.
	 */
	public EntityManager getEntityManager();

	/**
	 * Update or create entity of passed in type. If Id is null, the entity is
	 * created, otherwise, it is updated.
	 *
	 * Returns the updated entity.
	 */
	public <T, PK> T updateOrCreateEntity(T entity, PK id);

	/**
	 * Same as updateOrCreateEntity(T entity, PK id) but gets the id from the entity.
	 *
	 * Update or create entity of passed in type. If enitity.getId() null, the entity is
	 * created, otherwise, it is updated.
	 *
	 * Returns the updated entity.
	 */
//	public <T> T updateOrCreateEntity(T entity);
}
