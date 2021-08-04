package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.SocialMedia;

public class SocialMediaDaoImpl extends DefaultDao implements SocialMediaDao {

	@Override
	public SocialMedia findAll() {
		return getEntityManager().createNamedQuery("SocialMedia.findAll",SocialMedia.class).getResultList().get(0);
	}


}
