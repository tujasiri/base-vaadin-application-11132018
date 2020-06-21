package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.OrderSummary;

public interface OrderSummaryDao {
	
	public List<OrderSummary> findAll();
	public List<OrderSummary> findOrderRecord(long orid);

}
