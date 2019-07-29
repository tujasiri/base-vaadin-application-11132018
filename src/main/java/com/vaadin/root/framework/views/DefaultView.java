package com.vaadin.root.framework.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultView extends VerticalLayout implements View {

	BusinessInfo businessInfo;
	
//	public AboutView(BusinessInfo bi) {
	public DefaultView() {
		super();
		setProperties();
		buildLayout();
	}
	
	private void buildLayout(){
		StandardHeaderLayout standardHeader = UIUtils.getStandardHeaderLayout(2L);
		addComponents(standardHeader, new Label("ABOUTX"));
	}
	
	private void setProperties(){
		setHeight("100%");
		addStyleName("scrollable");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}	

}
