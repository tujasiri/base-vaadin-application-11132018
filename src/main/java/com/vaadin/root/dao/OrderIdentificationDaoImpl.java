package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.OrderIdentification;

public class OrderIdentificationDaoImpl extends DefaultDao implements OrderIdentificationDao {

	@Override
	public List<OrderIdentification> findAll() {
		return getEntityManager().createNamedQuery("OrderIdentification.findAll",OrderIdentification.class).getResultList();
	}

	@Override
	public List<OrderIdentification> findOrder(int orid) {
		return getEntityManager().createNamedQuery("OrderIdentification.findOrder",OrderIdentification.class).setParameter("orid",orid).getResultList();
	}

}
