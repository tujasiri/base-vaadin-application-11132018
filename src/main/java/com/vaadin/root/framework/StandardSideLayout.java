package com.vaadin.root.framework;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class StandardSideLayout extends VerticalLayout {
	
	public StandardSideLayout(/*featured item object*/){
		super();
		buildLayout();
	}
	
	public void buildLayout(){
		setWidth(20.0f, Unit.PERCENTAGE);
		addComponent(new Label("SIDE PANEL"));
	}

}
