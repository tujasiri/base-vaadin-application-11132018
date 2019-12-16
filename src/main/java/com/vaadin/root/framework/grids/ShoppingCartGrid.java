package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.model.MerchTable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.FooterRow;

public class ShoppingCartGrid extends Grid<MerchTable> {
	
	private	List<MerchTable>gridContainer = new ArrayList<>();

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
		this.setItems(CartSingleton.getInstance().getCheckoutCart().itemsInCart());
		this.setGridContainer(CartSingleton.getInstance().getCheckoutCart().itemsInCart());
		//add concatenated customizations to item desc	
		this.addColumn(MerchTable::getMtItemDescShort).setCaption("Item Description").setId("desc");
		this.addColumn(MerchTable::getMtItemPrice).setCaption("Cost").setId("cost");
	}
	
	
	private void refresh(){
		this.setItems(new ArrayList<>());
		this.setItems(this.getGridContainer());
	}

	public List<MerchTable> getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(List<MerchTable> gridContainer) {
		this.gridContainer = gridContainer;
	}


}
