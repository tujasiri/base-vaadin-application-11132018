package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.OrderIdentification;

public interface OrderIdentificationDao {
	
	public List<OrderIdentification> findAll();
	public List<OrderIdentification> findOrder(int orid);
	

}
