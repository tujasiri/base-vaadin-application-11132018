package com.vaadin.root.windows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.root.dao.DefaultDao;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.dto.Customizations;
import com.vaadin.root.framework.grids.CustomizationGrid;
import com.vaadin.root.framework.listeners.UpdateListener;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.MerchTable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ItemCustomizationWindow extends Window{

	private List<MerchTable> gridContainer;
	private List<MerchTable> testitems;
	private List<ItemCustomization> itemCustomizations = new ArrayList<>();
	private CustomizationGrid grid;
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private Button updateButton = new Button("Complete and Add to Cart");
	private Button closeButton = new Button("Close");
	private UpdateListener custUpdateLister;


	public ItemCustomizationWindow(List<MerchTable> gridcontainer, List<ItemCustomization> customization){

		this.setItemCustomizations(customization);

		this.getItemCustomizations().forEach(x->{
			System.out.println(String.format("cust id in item window customizations==>%d, index ==>%d",x.getIcId(),this.getItemCustomizations().indexOf(x)));
		});
		
		this.setModal(true);
		this.center();
		this.setGridContainer(gridcontainer);

		this.getGridContainer().forEach(xi->{
			System.out.println(String.format("cust id in item window gridContainer ==>%d",xi.getMtIcId()));
		});
		
		this.grid = new CustomizationGrid(gridcontainer);
		grid.setGridCustomizationContainer(this.getItemCustomizations());

		this.grid.setHeight(80.0f,Unit.PERCENTAGE);
		this.grid.setWidth(80.0f,Unit.PERCENTAGE);
		this.setGrid(grid);
		this.buildWindow();
		addListeners();
	}

	private void addListeners(){
		this.updateButton.addClickListener(u->{
			
			CartSingleton.getInstance().getCheckoutCart().setUpdateListener(getCustUpdateLister());


			if(!this.customizationsComplete()) {
				ConfirmDialog.show(UI.getCurrent(), "Confirmation",
					"Some customization fields have not be completed.  THIS REQUIRES CUSTOMIZATION.  Would you like to exit item editing and not add items to cart?" , "Yes", "No",
					new ConfirmDialog.Listener() {

						@Override
						public void onClose(ConfirmDialog dialog) {
							if (dialog.isConfirmed()) {
								//add update and insert of customizations	
								writeCustomiztionsToDb();
//								CartSingleton.getInstance().getCheckoutCart().addItemsCart(gridContainer);
								close();
							} else {
								dialog.close();
								Notification.show("you clicked no");
							}
						}
					});
			}else {
				//add update and insert of customizations	
				writeCustomiztionsToDb();
				associateCustomizationsWithMerchItems();
				//add customized items to cart
				System.out.println("merchItem==>"+getGridContainer().toString());
				
				close();
				
			}			
		});

		this.closeButton.addClickListener(e->{
			
			if(!this.customizationsComplete()) {
				ConfirmDialog.show(UI.getCurrent(), "Confirmation",
					"Are you sure you want to close w/o saving your item edits?" , "Yes", "No",
					new ConfirmDialog.Listener() {

						@Override
						public void onClose(ConfirmDialog dialog) {
							if (dialog.isConfirmed()) {
								close();
							} else {
								dialog.close();
								Notification.show("you clicked no");
							}
						}
					});
			}else {
				close();
			}
		});
		
		
	}
   

	private void buildWindow(){
    	//check for container setting
    	this.setWidth(50.0f, Unit.PERCENTAGE);
    	this.setHeight(50.0f, Unit.PERCENTAGE);
    	this.buttonLayout.addComponents(this.updateButton,this.closeButton);
    	this.layout.addComponents(this.getGrid(),this.buttonLayout);
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
	
	public List<ItemCustomization> getItemCustomizations() {
			return itemCustomizations;
	}

	public void setItemCustomizations(List<ItemCustomization> itemCustomizations) {
		this.itemCustomizations = itemCustomizations;
	}
	
	public boolean customizationsComplete(){
		return this.getItemCustomizations().stream()
									.filter(x->x.getIcColor() == null || x.getIcSize() == null || x.getIcGender() == null)
									.collect(Collectors.toList())
									.isEmpty();
	}

	public void writeCustomiztionsToDb(){
		DefaultDao dao = new DefaultDao();
		List<ItemCustomization> icList = new ArrayList<>();

		getItemCustomizations().stream().forEach(ic->{
			icList.add(dao.updateOrCreateEntity(ic,ic.getIcId()));
		});
		
		//update local customization list after writing customizations to db
		setItemCustomizations(icList);
	
	}
	
	
	public void associateCustomizationsWithMerchItems(){
		//ASSOCIATION IS WRONG ---INVESTIGATE!
		List<MerchTable> merchTableAssocList = new ArrayList<>();
		
		int index=0;
		
		for(MerchTable mt :this.getGridContainer()){
			mt.setMtIcId(getItemCustomizations().get(index++).getIcId());
			merchTableAssocList.add(mt);
		}
		this.setGridContainer(merchTableAssocList);
		
		
		CartSingleton.getInstance().getCheckoutCart().setUpdateListener(this.getCustUpdateLister());
		
		System.out.println("UPDATED gridContainer ==>"+this.getGridContainer().toString());
		CartSingleton.getInstance().getCheckoutCart().addItemsCart(gridContainer);
		System.out.println("CART==>"+CartSingleton.getInstance().getCheckoutCart().toString());
		System.out.println("NUMBER OF ITMES IN CART==>"+CartSingleton.getInstance().getCheckoutCart().itemsInCart().size());
			
	}

	public UpdateListener getCustUpdateLister() {
		return custUpdateLister;
	}

	public void setCustUpdateLister(UpdateListener custUpdateLister) {
		this.custUpdateLister = custUpdateLister;
	}
	
	
}
