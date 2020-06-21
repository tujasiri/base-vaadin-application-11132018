package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.OrderSummary;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.renderers.ButtonRenderer;

public class ShoppingCartGrid extends Grid<OrderSummary> {
	
	private	List<OrderSummary>gridContainer = new ArrayList<>();

	public ShoppingCartGrid() {
		addListeners();
		buildGrid();
		addTotalRow();
	}
	
	private void addListeners(){
	}

	private void addTotalRow(){
		System.out.println("# of items in container==>"+this.getGridContainer().size());
		FooterRow row = this.appendFooterRow();
		row.getCell("cost").setText(String.format("%2.0f",CartSingleton.getInstance().getCheckoutCart().calculateTotal()));
		row.getCell("desc").setText("Total:");
	}

	private void buildGrid(){
		this.setItems(CartSingleton.getInstance().getCheckoutCart().getOrderSummary());
		this.setGridContainer(CartSingleton.getInstance().getCheckoutCart().getOrderSummary());
		//add concatenated customizations to item desc	
		this.addColumn(OrderSummary::getMtItemDescShort).setCaption("Item Description").setId("desc");
		this.addColumn(OrderSummary::getMtItemPrice).setCaption("Cost").setId("cost");
		
		//if item is customizable
		this.addColumn(edit -> "Edit Item",
                new ButtonRenderer(clickEvent -> {
       }));
		
		
		this.addColumn(edit -> "Delete Item",
                new ButtonRenderer(clickEvent -> {
                	Notification.show("DELETE CLICKED!...");
                	
                	OrderSummary mt = (OrderSummary)clickEvent.getItem();
                	
        			CartSingleton.getInstance().getCheckoutCart().removeOrderItemFromCart(mt.getOrSeq());

        			this.refresh();
       }));
	}
	
	
	public void refresh(){
		this.setItems(new ArrayList<>());
		this.setGridContainer(CartSingleton.getInstance().getCheckoutCart().getOrderSummary());
		this.setItems(this.getGridContainer());

		FooterRow row = this.getFooterRow(0);
		row.getCell("cost").setText(String.format("%2.0f",CartSingleton.getInstance().getCheckoutCart().calculateTotal()));
//		row.getCell("desc").setText("Total:");
	}

	public List<OrderSummary> getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(List<OrderSummary> gridContainer) {
		this.gridContainer = gridContainer;
	}


}
