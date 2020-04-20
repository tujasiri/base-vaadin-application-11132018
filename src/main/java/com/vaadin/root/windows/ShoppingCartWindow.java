package com.vaadin.root.windows;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.framework.grids.CustomizationGrid;
import com.vaadin.root.framework.grids.ShoppingCartGrid;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.MerchTable;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ShoppingCartWindow extends Window {
	private List<MerchTable> gridContainer;
	private List<MerchTable> testitems;
	private ShoppingCartGrid grid = new ShoppingCartGrid();
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private Button emptyButton = new Button("Empty Cart");
	private Button closeButton = new Button("Close");
	private Button checkOutButton = new Button("Check Out");

	public ShoppingCartWindow() {
		buildWindow();
		addListeners();
	}
	
	public void addListeners(){
		this.emptyButton.addClickListener(eButton->{
			
			Notification.show("CLICKED!");
			CartSingleton.getInstance().getCheckoutCart().emptyCart();
			this.grid.refresh();
		});

		this.closeButton.addClickListener(cButton->{
			this.close();
		});
		
		this.checkOutButton.addClickListener(coButton->{
			UI.getCurrent().getNavigator().navigateTo("checkout");
			this.close();
		});
	}

	public void buildWindow(){
		this.setHeight(50.0f,Unit.PERCENTAGE);
		this.setWidth(50.0f,Unit.PERCENTAGE);
		
		this.buttonLayout.addComponents(this.emptyButton,this.checkOutButton,this.closeButton);
    	this.layout.addComponents(this.grid,this.buttonLayout);
		this.layout.setComponentAlignment(this.grid, Alignment.BOTTOM_CENTER);
		this.setContent(layout);
//		layout.addComponents(grid);
	}

}
//