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
//		ComboBox<RSize>cbSize = new ComboBox<RSize>(); 
		ComboBox<Integer>cbSize = new ComboBox<Integer>(); 
		
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
			return this.getGridCustomizationContainer().get(this.gridContainer.indexOf(gender)).getIcColor();
//			return getCustomizationParm();
		}).setCaption("Gender");
		
		
		this.addColumn(edit -> "Edit Item",
                new ButtonRenderer(clickEvent -> {
//                    this.getEditor(); //????
//			this.getGridCustomizationContainer().get(this.gridContainer.indexOf(gender)).getIcColor();
                	
                	
                	
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

                	  });
                	  
                	  
                	  UI.getCurrent().addWindow(selectCustWindow);
              }));	
		
//		this.addColumn(new ValueProvider<Integer,Integer>(){
//
////			@Override
////			public Object apply(Object source) {
////				// TODO Auto-generated method stub
////				return null;
////			}
//
//			@Override
//			public Integer apply(Integer source) {
//				// TODO Auto-generated method stub
//				return cbSize.getValueh() ;
//			}
//			
//		}).setCaption("Size").setWidth(100);
		
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




	private ListDataProvider<Integer> getSizeComboDataProvider(){
		List<Integer> cbData = new ArrayList<>();
		
		for(int i=1;i<11;i++)
			cbData.add(i);
		
		ListDataProvider<Integer> cbDataProvider = new ListDataProvider<>(cbData);
		
		return cbDataProvider;
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
		this.setItems(new ArrayList<>());
		this.setItems(this.getGridContainer());
	}



}
