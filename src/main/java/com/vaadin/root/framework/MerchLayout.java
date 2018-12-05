package com.vaadin.root.framework;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
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
	private ComboBox<Integer> qty = new ComboBox("Qty.:");
	
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
		this.merchImage.setWidth(100.0f,Unit.PERCENTAGE);
		this.merchCost.setCaption(String.format("Cost: %f", this.merchTableItem.getMtItemPrice()));
		
		this.buttonLayout.setSizeFull();
//		this.buttonLayout.setMargin(true);
		this.buttonLayout.setSpacing(true);
		
		this.qty.setDataProvider(getComboDataProvider());
		
		this.buttonLayout.addComponents(this.viewButton, this.merchButton, this.qty);
		this.buttonLayout.setComponentAlignment(this.viewButton, Alignment.MIDDLE_LEFT);
		this.buttonLayout.setComponentAlignment(this.merchButton, Alignment.MIDDLE_LEFT);
		
		addComponents(this.merchDesc, this.merchImage, this.merchCost, this.qty, this.buttonLayout);
		setComponentAlignment(this.qty, Alignment.MIDDLE_LEFT);
		setComponentAlignment(this.buttonLayout, Alignment.MIDDLE_LEFT);
	}
	
	private ListDataProvider<Integer> getComboDataProvider(){
		List<Integer> cbData = new ArrayList<>();
		
		for(int i=1;i<11;i++)
			cbData.add(i);
		
		ListDataProvider<Integer> cbDataProvider = new ListDataProvider<>(cbData);
		
		return cbDataProvider;
	}

}
