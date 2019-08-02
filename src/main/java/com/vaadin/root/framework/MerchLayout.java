package com.vaadin.root.framework;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.root.StandardComponent;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.dto.CheckoutCart;
import com.vaadin.root.framework.grids.CustomizationGrid;
import com.vaadin.root.jscomponent.TimerComponent;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.root.windows.ItemCustomizationWindow;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MerchLayout extends VerticalLayout{
	
	private MerchTable merchTableItem;
	private Image merchImage;
	private Label merchCost = new Label();
	private Label merchDesc = new Label();
	private Button merchButton = UIUtils.createShoppingButton();
	private Button viewButton = UIUtils.createViewButton();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private HorizontalLayout imageLayout = new HorizontalLayout();
	private VerticalLayout lowerComponentLayout = new VerticalLayout();
	private ComboBox<Integer> qty = new ComboBox("Qty.:");
//	private StandardComponent standard = new StandardComponent();
	private TimerComponent standard = new TimerComponent();      
	private CheckoutCart checkoutCart = CartSingleton.getInstance().getCheckoutCart();
	private List<MerchTable> itemsToBeAdded = new ArrayList<MerchTable>();

	
	public MerchLayout(MerchTable merchTableItem){
		super();
		this.merchTableItem = merchTableItem;
		buildLayout();
		addListeners();
	}
	
	public void buildLayout(){
		setSizeFull();
//		setMargin(true);
		setSpacing(true);
		
		this.merchDesc.setCaption(this.merchTableItem.getMtItemDescShort());
		this.merchImage = UIUtils.byteArrayToImage(this.merchTableItem.getMtImage());
//		this.merchImage.setWidth(100.0f,Unit.PERCENTAGE);
		
		this.imageLayout.setSizeFull();
//		this.imageLayout.setSizeUndefined();
		this.imageLayout.setSpacing(true);
		this.imageLayout.addComponent(this.merchImage);
		this.imageLayout.setExpandRatio(this.merchImage, 1.0f);
		
		
		this.merchCost.setCaption(String.format("Cost: %.02f", this.merchTableItem.getMtItemPrice()));
		
		this.lowerComponentLayout.setSizeFull();
		this.lowerComponentLayout.setSpacing(true);
		
		this.buttonLayout.setSizeFull();
//		this.buttonLayout.setMargin(true);
		this.buttonLayout.setSpacing(true);
		
		this.qty.setDataProvider(getComboDataProvider());
		
		this.buttonLayout.addComponents(this.merchButton, this.viewButton);
		this.buttonLayout.setExpandRatio(this.merchButton, 1.0f);
		this.buttonLayout.setExpandRatio(this.viewButton, 1.0f);
		this.buttonLayout.setComponentAlignment(this.viewButton, Alignment.MIDDLE_LEFT);
		this.buttonLayout.setComponentAlignment(this.merchButton, Alignment.MIDDLE_LEFT);
		
//		lowerComponentLayout.addComponents(this.merchCost, this.qty, this.buttonLayout);
		this.lowerComponentLayout.addComponents(this.merchCost, this.qty, this.merchButton, this.viewButton);
		
		this.lowerComponentLayout.setExpandRatio(this.merchCost, 1.0f);
		this.lowerComponentLayout.setExpandRatio(this.qty, 1.0f);
		this.lowerComponentLayout.setExpandRatio(this.merchButton, 1.0f);
		this.lowerComponentLayout.setExpandRatio(this.viewButton, 1.0f);
		
//		addComponents(this.merchDesc, this.merchImage, this.merchCost, this.qty, this.buttonLayout);
		addComponents(this.merchDesc, this.imageLayout, this.lowerComponentLayout, this.standard);
		
		setExpandRatio(this.merchDesc, 1.0f);
		setExpandRatio(this.imageLayout, 1.0f);
		setExpandRatio(this.lowerComponentLayout, 1.0f);
//		setComponentAlignment(this.qty, Alignment.MIDDLE_LEFT);
//		setComponentAlignment(this.buttonLayout, Alignment.MIDDLE_LEFT);
	}
	
	private ListDataProvider<Integer> getComboDataProvider(){
		List<Integer> cbData = new ArrayList<>();
		
		for(int i=1;i<11;i++)
			cbData.add(i);
		
		ListDataProvider<Integer> cbDataProvider = new ListDataProvider<>(cbData);
		
		return cbDataProvider;
	}
	
	private void addListeners(){
		
		this.viewButton.addListener(e->{
			this.standard.jquerytest();
		});
		
		this.merchButton.addListener(e->{

			//customize item if there are varying options
			//consider quantity
			this.itemsToBeAdded = new ArrayList<MerchTable>();
			int itemQuantity = this.qty.getValue().intValue();

			for (int i=0;i < itemQuantity;i++){
				itemsToBeAdded.add(this.merchTableItem);
			}

			checkoutCart.addItemToCart(this.merchTableItem);
			
			ItemCustomizationWindow customWindow = new ItemCustomizationWindow(itemsToBeAdded);
			UI.getCurrent().addWindow(customWindow);
			
		});
		
	}

}
