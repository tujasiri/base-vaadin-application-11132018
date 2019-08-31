package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.RColor;
import com.vaadin.root.model.RGender;
import com.vaadin.root.model.RSize;

public interface RTableDao {
	public List<RSize> findAllSize();
	public List<String> findAllSizeStrings();
	public List<RColor> findAllColor();
	public List<String> findAllColorStrings();
	public List<RGender> findAllGender();
	public List<String> findAllGenderStrings();

}
