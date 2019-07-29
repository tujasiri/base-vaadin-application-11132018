package com.vaadin.root.dao;

public class DefaultDataService extends DefaultDao implements DataService {
	
	private static DefaultDataService instance = new DefaultDataService();
	private static final MerchDao merchDao = new MerchDaoImpl();
	private static final BusinessInfoDao businessInfoDao = new BusinessInfoDaoImpl();
			
	private DefaultDataService(){} 
	
	public static DefaultDataService getInstance(){
		return instance;
	}

	@Override
	public MerchDao getMerchDao() {
		return merchDao;
	}

	@Override
	public BusinessInfoDao getBusinessInfoDao() {
		return businessInfoDao;
	}
	
	


}
