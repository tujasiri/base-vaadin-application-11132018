package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.MerchTable;

public class MerchDaoImpl extends DefaultDao implements MerchDao {

	@Override
	public List<MerchTable> findAll() {
		return getEntityManager().createNamedQuery("MerchTable.findAll",MerchTable.class).getResultList();
	}

	@Override
	public MerchTable findOneRecord(int idx) {
		return getEntityManager().createNamedQuery("MerchTable.findOneRecord",MerchTable.class).setParameter("idx",idx).getSingleResult();
	}

}
