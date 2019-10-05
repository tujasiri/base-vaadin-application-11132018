package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.model.MerchTable;
import com.vaadin.ui.Grid;

public class ShoppingCartGrid extends Grid<MerchTable> {
	
	private	List<MerchTable>gridContainer = new ArrayList<>();

	public ShoppingCartGrid() {
		addListeners();
		buildGrid();
	}
	
	private void addListeners(){
	}

	private void buildGrid(){
		this.setItems(CartSingleton.getInstance().getCheckoutCart().itemsInCart());
		this.setGridContainer(CartSingleton.getInstance().getCheckoutCart().itemsInCart());
		//add concatenated customizations to item desc	
		this.addColumn(MerchTable::getMtItemDescShort).setCaption("Item Description");
		this.addColumn(MerchTable::getMtItemPrice).setCaption("Cost");
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
