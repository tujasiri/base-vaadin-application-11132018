package com.vaadin.root.dao;

import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.MerchTableOLD_;

public class BusinessInfoDaoImpl extends DefaultDao implements BusinessInfoDao {

	@Override
	public BusinessInfo findById(Long id) {
		return getEntityManager().createNamedQuery("BusinessInfo.findById",BusinessInfo.class).setParameter("idx",id).getSingleResult();

	}
	

}
