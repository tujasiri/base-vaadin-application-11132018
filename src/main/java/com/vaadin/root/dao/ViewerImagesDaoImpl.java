package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.ViewerImage;

public class ViewerImagesDaoImpl extends DefaultDao implements ViewerImagesDao {

	@Override
	public List<ViewerImage> findAll() {
		return getEntityManager().createNamedQuery("ViewerImage.findAll",ViewerImage.class).getResultList();

	}

	@Override
	public List<ViewerImage> findById(int idx) { 
		return getEntityManager().createNamedQuery("ViewerImage.findById",ViewerImage.class).setParameter("idx", idx).getResultList();
	}
	

}
