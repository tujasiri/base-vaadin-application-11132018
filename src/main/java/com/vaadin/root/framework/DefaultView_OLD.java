package com.vaadin.root.framework;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultView_OLD extends VerticalLayout implements View {

	BusinessInfo businessInfo;
	
//	public AboutView(BusinessInfo bi) {
	public DefaultView_OLD() {
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
