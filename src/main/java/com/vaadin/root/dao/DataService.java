package com.vaadin.root.dao;

import com.vaadin.root.model.OrderSummary;

public interface DataService {
	
	public MerchDao getMerchDao();
	public BusinessInfoDao getBusinessInfoDao();
	public RTableDao getRTableDao();
	public OrderSummaryDao getOrderSummaryDao();
	public ViewerImagesDao getViewerImagesDao();

}
