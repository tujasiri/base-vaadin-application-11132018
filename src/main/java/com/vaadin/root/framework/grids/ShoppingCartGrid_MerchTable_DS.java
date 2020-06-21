package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.MerchTable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.renderers.ButtonRenderer;

public class ShoppingCartGrid_MerchTable_DS extends Grid<MerchTable> {
	
	private	List<MerchTable>gridContainer = new ArrayList<>();

	public ShoppingCartGrid_MerchTable_DS() {
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
		this.setItems(CartSingleton.getInstance().getCheckoutCart().itemsInCart());
		this.setGridContainer(CartSingleton.getInstance().getCheckoutCart().itemsInCart());
		//add concatenated customizations to item desc	
		this.addColumn(MerchTable::getMtItemDescShort).setCaption("Item Description").setId("desc");
		this.addColumn(MerchTable::getMtItemPrice).setCaption("Cost").setId("cost");
		
		//if item is customizable
		this.addColumn(edit -> "Edit Item",
                new ButtonRenderer(clickEvent -> {
       }));
		
		
		this.addColumn(edit -> "Delete Item",
                new ButtonRenderer(clickEvent -> {
                	Notification.show("DELETE CLICKED!...");
                	
                	MerchTable mt = (MerchTable)clickEvent.getItem();
        			CartSingleton.getInstance().getCheckoutCart().removeIndividualItem(mt);

        			this.refresh();
       }));
	}
	
	
	public void refresh(){
		this.setItems(new ArrayList<>());
		this.setGridContainer(CartSingleton.getInstance().getCheckoutCart().itemsInCart());
		this.setItems(this.getGridContainer());

		FooterRow row = this.getFooterRow(0);
		row.getCell("cost").setText(String.format("%2.0f",CartSingleton.getInstance().getCheckoutCart().calculateTotal()));
//		row.getCell("desc").setText("Total:");
	}

	public List<MerchTable> getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(List<MerchTable> gridContainer) {
		this.gridContainer = gridContainer;
	}


}
