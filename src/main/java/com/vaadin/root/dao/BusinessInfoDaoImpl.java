package com.vaadin.root.dao;

import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.MerchTable;

public class BusinessInfoDaoImpl extends DefaultDao implements BusinessInfoDao {

	@Override
	public BusinessInfo findById(Long id) {
		return getEntityManager().createNamedQuery("BusinessInfo.findById",BusinessInfo.class).setParameter("idx",id).getSingleResult();

	}
	

}
