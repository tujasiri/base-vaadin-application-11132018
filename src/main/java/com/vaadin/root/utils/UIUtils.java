package com.vaadin.root.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.framework.StandardFooterLayout;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;

public class UIUtils {
	
	public static Image byteArrayToImage(final byte[]byteArray){
		StreamSource streamSource = new StreamResource.StreamSource(){

			@Override
			public InputStream getStream() {
				return new ByteArrayInputStream(byteArray);
			}
		}; 
		
		return new Image(null, new StreamResource(streamSource,""));
		
	}
	
	public static Button createShoppingButton(){
		Button shoppingButton = new Button("Add to Cart",VaadinIcons.CART);
		return shoppingButton; 
	}
	
	public static Button createViewButton(){
		Button viewButton = new Button("View Item Details", VaadinIcons.GLASSES);
		
		return viewButton; 
	}
	
	public static StandardHeaderLayout getStandardHeaderLayout(Long businessId){
		return new StandardHeaderLayout( DefaultDataService.getInstance().getBusinessInfoDao().findById(businessId));
	}
	
	public static StandardFooterLayout getStandardFooterLayout(Long businessId){
		return new StandardFooterLayout( DefaultDataService.getInstance().getBusinessInfoDao().findById(businessId));
	}
	
	
}
