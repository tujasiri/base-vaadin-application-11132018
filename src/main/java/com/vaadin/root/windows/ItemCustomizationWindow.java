package com.vaadin.root.windows;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dto.Customizations;
import com.vaadin.root.framework.grids.CustomizationGrid;
import com.vaadin.root.model.MerchTable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ItemCustomizationWindow extends Window{

	private List<MerchTable> gridContainer;
	private List<MerchTable> testitems;
	private CustomizationGrid grid;
	private VerticalLayout layout = new VerticalLayout();

	public ItemCustomizationWindow(List<MerchTable> gridcontainer){
		this.setModal(true);
		this.center();
		this.setGridContainer(gridcontainer);
		this.grid = new CustomizationGrid(gridcontainer);
		this.grid.setHeight(80.0f,Unit.PERCENTAGE);
		this.grid.setWidth(80.0f,Unit.PERCENTAGE);
		this.setGrid(grid);
		this.buildWindow();
	}

    private void buildWindow(){
    	//check for container setting
    	this.setWidth(50.0f, Unit.PERCENTAGE);
    	this.setHeight(50.0f, Unit.PERCENTAGE);
    	this.layout.addComponents(this.getGrid());
		this.layout.setComponentAlignment(this.grid, Alignment.BOTTOM_CENTER);

    	this.setContent(this.layout);

    }

	public List<MerchTable> getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(List<MerchTable> gridContainer) {
		this.gridContainer = gridContainer;
	}

	public CustomizationGrid getGrid() {
		return grid;
	}

	public void setGrid(CustomizationGrid grid) {
		this.grid = grid;
	}

	public VerticalLayout getLayout() {
		return layout;
	}

	public void setLayout(VerticalLayout layout) {
		this.layout = layout;
	}

}
