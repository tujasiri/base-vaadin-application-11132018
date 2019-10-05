package com.vaadin.root.windows;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.framework.grids.CustomizationGrid;
import com.vaadin.root.framework.grids.ShoppingCartGrid;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.MerchTable;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ShoppingCartWindow extends Window {
	private List<MerchTable> gridContainer;
	private List<MerchTable> testitems;
	private ShoppingCartGrid grid = new ShoppingCartGrid();
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private Button updateButton = new Button("Complete and Add to Cart");
	private Button closeButton = new Button("Close");

	public ShoppingCartWindow() {
		addListeners();
		buildWindow();
	}
	
	public void addListeners(){
	}

	public void buildWindow(){
		this.setHeight(50.0f,Unit.PERCENTAGE);
		this.setWidth(50.0f,Unit.PERCENTAGE);

		layout.addComponents(grid);
		this.setContent(layout);
		

	}
}
