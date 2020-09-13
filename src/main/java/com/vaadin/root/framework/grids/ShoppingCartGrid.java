package com.vaadin.root.framework.grids;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dao.DefaultDao;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.framework.listeners.UpdateListener;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.OrderSummary;
import com.vaadin.root.windows.SelectCustomizationWindow;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.renderers.ButtonRenderer;

public class ShoppingCartGrid extends Grid<OrderSummary> {
	
	private	List<OrderSummary>gridContainer = new ArrayList<>();
	private	DefaultDao dao = new DefaultDao();


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

	@SuppressWarnings("unchecked")
	private void buildGrid(){
		this.setItems(CartSingleton.getInstance().getCheckoutCart().getOrderSummary());
		this.setGridContainer(CartSingleton.getInstance().getCheckoutCart().getOrderSummary());
		//add concatenated customizations to item desc	
		this.addColumn(OrderSummary::getMtItemDescShort).setCaption("Item Description").setId("desc");
		this.addColumn(OrderSummary::getMtItemPrice).setCaption("Cost").setId("cost");
		this.addColumn(OrderSummary::getCustomizationDetails).setCaption("Customization Details").setId("customization");
		
		this.addColumn(edit -> "Edit Item",
                new ButtonRenderer(clickEvent -> {
                	
                	//MerchTable mt = (MerchTable)clickEvent.getItem();
                	OrderSummary orderSummary = (OrderSummary)clickEvent.getItem();
                	
                	//if item is customizable allow edit, otherwise notify user that item is not customizable
                	if(!orderSummary.getMtCustomizeable()) {
                		Notification.show("Item cannot be edited.",Notification.TYPE_WARNING_MESSAGE);
                	}else {
                		
						ItemCustomization currentCustomization = dao.getEntityManager().find(ItemCustomization.class, orderSummary.getIcId());
						
						System.out.println("idk orderSumamry ItemNum ==>"+orderSummary.getMtItemNum());
						System.out.println("idk orderSumamry icid ==>"+orderSummary.getMtIcId());
						System.out.println("orderSummary element index ==>"+ this.gridContainer.indexOf(orderSummary));
						
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

								dao.updateOrCreateEntity(currentCustomization, currentCustomization.getIcId());
								
								//update OrderSummary in cart so customizations are reflected in grid real time!
								CartSingleton.getInstance().getCheckoutCart().getOrderSummary().stream()
											.filter(x->(x.getIcId() == orderSummary.getIcId()))
											.forEach(y->{ y.setIcColor(color);
														  y.setIcGender(gender);
														  y.setIcSize(size); });

								refresh();
							}

							@Override
							public void updateObject() {
								// TODO Auto-generated method stub
								
							}});
						  
						  selectCustWindow.getCbSize().setValue(currentCustomization.getIcSize());
						  selectCustWindow.getCbColor().setValue(currentCustomization.getIcColor());
						  selectCustWindow.getCbGender().setValue(currentCustomization.getIcGender());
						  
						  UI.getCurrent().addWindow(selectCustWindow);
                	}

       })).setId("editItem");
		
		
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
		System.out.println("ORDER SUMMARY==>"+CartSingleton.getInstance().getCheckoutCart().getOrderSummary().toString());
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
