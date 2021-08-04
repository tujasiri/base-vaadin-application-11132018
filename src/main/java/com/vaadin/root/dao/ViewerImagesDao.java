package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.ViewerImage;

public interface ViewerImagesDao {
	
	public List<ViewerImage> findAll();
	public List<ViewerImage> findById(int idx);

}
