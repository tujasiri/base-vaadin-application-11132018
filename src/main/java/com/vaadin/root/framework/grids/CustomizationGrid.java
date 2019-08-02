package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.model.MerchTable;
import com.vaadin.ui.Grid;

//public class CustomizationGrid<MerchTable> extends Grid{
public class CustomizationGrid extends Grid<MerchTable>{
	
	List<MerchTable>gridContainer;
	
	public CustomizationGrid(){
	}
	

	public CustomizationGrid(List<MerchTable> customContainer){
//		super();
		this.setGridContainer(customContainer);
		buildGrid();
	}

	public void buildGrid(){
		this.setItems(this.getGridContainer());
		this.addColumn(MerchTable::getMtItemDescShort).setCaption("Item Description");
		this.addColumn(MerchTable::getMtItemPrice).setCaption("Cost");
	}

	public List<MerchTable> getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(List<MerchTable> gridContainer) {
		this.gridContainer = gridContainer;
	}

}
