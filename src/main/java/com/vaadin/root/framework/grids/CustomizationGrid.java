package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.root.framework.listeners.UpdateListener;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.RSize;
import com.vaadin.root.windows.SelectCustomizationWindow;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ButtonRenderer;

//public class CustomizationGrid<MerchTable> extends Grid{
public class CustomizationGrid extends Grid<MerchTable>{
	
	List<MerchTable>gridContainer = new ArrayList<>();
	List<ItemCustomization>gridCustomizationContainer = new ArrayList<>();
	
	public CustomizationGrid(){
	}

	public CustomizationGrid(List<MerchTable> customContainer){
//		super();
		this.setGridContainer(customContainer);
		buildGrid();
	}

	public void buildGrid(){
		this.getGridContainer().forEach(y->{
			System.out.println(String.format("mt_ic_id in buildGrid()==>%s ",y.getMtIcId()));
		});
		
		this.setItems(this.getGridContainer());
		this.addColumn(MerchTable::getMtItemDescShort).setCaption("Item Description");
		this.addColumn(MerchTable::getMtItemPrice).setCaption("Cost");
		
		this.addColumn(size -> {
			return this.getGridCustomizationContainer().get(this.gridContainer.indexOf(size)).getIcSize();
//			return getCustomizationParm();
		}).setCaption("Size");
		
		this.addColumn(color -> {
			return this.getGridCustomizationContainer().get(this.gridContainer.indexOf(color)).getIcColor();
//			return getCustomizationParm();
		}).setCaption("Color");
		
		this.addColumn(gender -> {
			return this.getGridCustomizationContainer().get(this.gridContainer.indexOf(gender)).getIcGender();
//			return getCustomizationParm();
		}).setCaption("Gender");
		
		this.addColumn(edit -> "Edit Item",
                new ButtonRenderer(clickEvent -> {
                	
                	MerchTable mt = (MerchTable)clickEvent.getItem();
                	ItemCustomization currentCustomization = this.getGridCustomizationContainer().get(this.gridContainer.indexOf(mt));
                	
                	System.out.println("idk ==>"+mt.getMtItemNum());
                	System.out.println("element index ==>"+ this.gridContainer.indexOf(mt));
                	
                	
                	  SelectCustomizationWindow selectCustWindow = new SelectCustomizationWindow();
                	  
                	  selectCustWindow.setUpdatelistener(new UpdateListener() {

						@Override
						public void updateRecord() {
							
						}

						@Override
						public void updateCustomizationRecord(String size, String color, String gender) {
							currentCustomization.setIcSize(size != null ? size : currentCustomization.getIcSize());
							currentCustomization.setIcColor(color != null ? color : currentCustomization.getIcColor());
							currentCustomization.setIcGender(gender != null ? gender : currentCustomization.getIcGender());
							refresh();
						}

						@Override
						public void updateObject() {
						}
						
                	  });
                	  
                	  selectCustWindow.getCbSize().setValue(currentCustomization.getIcSize());
                	  selectCustWindow.getCbColor().setValue(currentCustomization.getIcColor());
                	  selectCustWindow.getCbGender().setValue(currentCustomization.getIcGender());
                	  
                	  UI.getCurrent().addWindow(selectCustWindow);
              }));	
		
		
		this.getGridContainer().forEach(y->{
			System.out.println(String.format("mt_ic_id==>%s ",y.getMtIcId()));
		});
	}
	
	public List<ItemCustomization> getGridCustomizationContainer() {
		return gridCustomizationContainer;
	}
	
	public void setGridCustomizationContainer(List<ItemCustomization> itemcustomization) {
		this.gridCustomizationContainer = itemcustomization;
	}

	public List<MerchTable> getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(List<MerchTable> gridContainer) {
		this.gridContainer = gridContainer;
	}
	
	private String getCustomizationParm(){
		return "";
		
	}
	
	private void refresh(){
		
//		System.out.println("IN  REFRESH!");
		this.setItems(new ArrayList<>());
		this.setItems(this.getGridContainer());
	}



}
