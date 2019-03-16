package com.vaadin.root.framework;

import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;

public class StandardFooterLayout extends HorizontalLayout {
	
	BusinessInfo businessInfo;
	Image businessLogo;
	
	public StandardFooterLayout(BusinessInfo bi) {
		super();
		this.businessInfo = bi;
		this.businessLogo = UIUtils.byteArrayToImage(this.businessInfo.getBiLogo());
//		buildLayout();
	}
		
	

}
