package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.OrderSummary;

public class OrderSummaryDaoImpl extends DefaultDao implements OrderSummaryDao {

	@Override
	public List<OrderSummary> findAll() {
		return getEntityManager().createNamedQuery("OrderSummary.findAll",OrderSummary.class).getResultList();
	}

	@Override
	public List<OrderSummary> findOrderRecord(long orid) {
		return getEntityManager().createNamedQuery("OrderSummary.findOrderRecord",OrderSummary.class).setParameter("orid",orid).getResultList();
	}

}
