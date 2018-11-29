package com.vaadin.root.dao;

import java.util.List;

import com.vaadin.root.model.MerchTable;

public interface MerchDao {
	
	public List<MerchTable> findAll();
	public MerchTable findOneRecord(int idx);
	

}
