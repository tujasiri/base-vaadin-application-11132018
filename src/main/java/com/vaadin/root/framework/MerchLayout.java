package com.vaadin.root.framework;

import com.vaadin.root.model.MerchTable;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MerchLayout extends VerticalLayout{
	
	private MerchTable merchTableItem;
	private Image merchImage;
	private Label merchCost = new Label();
	private Label merchDesc = new Label();
	private Button merchButton = UIUtils.createShoppingButton();
	private Button viewButton = UIUtils.createViewButton();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	
	public MerchLayout(MerchTable merchTableItem){
		super();
		this.merchTableItem = merchTableItem;
		buildLayout();
	}
	
	public void buildLayout(){
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		
		this.merchDesc.setCaption(this.merchTableItem.getMtItemDescShort());
		this.merchImage = UIUtils.byteArrayToImage(this.merchTableItem.getMtImage());
		this.merchCost.setCaption(String.format("Cost: %f", this.merchTableItem.getMtItemPrice()));
		
		this.buttonLayout.setSizeFull();
		this.buttonLayout.setMargin(true);
		this.buttonLayout.setSpacing(true);
		
		this.buttonLayout.addComponents(this.viewButton, this.merchButton);
		
		addComponents(this.merchDesc, this.merchImage, this.merchCost, this.buttonLayout);
		
		
		
		
		
	}

}
