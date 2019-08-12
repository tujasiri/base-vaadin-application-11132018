package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.RSize;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.renderers.ButtonRenderer;

//public class CustomizationGrid<MerchTable> extends Grid{
public class CustomizationGrid extends Grid<MerchTable>{
	
	List<MerchTable>gridContainer = new ArrayList<MerchTable>();
	
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
//		this.addComponentColumn(new ValueProvider<MerchTable::getMtItemDescShort,Object>(){
		this.addColumn(t-> {
			System.out.println(String.format("MtIcId ==> %d",t.getMtIcId()));
			return "TEST";
		});
		
		
		this.addColumn(person -> "Edit",
                new ButtonRenderer(clickEvent -> {
//                    this.getEditor(); //????
                	  Notification.show("clicked");
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

}
