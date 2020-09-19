package com.vaadin.root.framework;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.root.StandardComponent;
import com.vaadin.root.dao.DefaultDao;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.dto.CheckoutCart;
import com.vaadin.root.framework.grids.CustomizationGrid;
import com.vaadin.root.framework.listeners.UpdateListener;
import com.vaadin.root.jscomponent.TimerComponent;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.utils.UIConstants;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
	private final static Logger logger = LoggerFactory.getLogger(MerchLayout.class);
	private UpdateListener cartUpdateListener;
	

	public MerchLayout(MerchTable x){
		super();
		this.merchTableItem = x;
		buildLayout();
		addListeners();
	}
	
	public void buildLayout(){
		setSizeFull();
//		setMargin(true);
		setSpacing(true);
		
		this.merchDesc.setCaption(this.merchTableItem.getMtItemDescShort());
		this.merchImage = UIUtils.byteArrayToImage(this.merchTableItem.getMtImage());
		this.merchImage.setHeight("300px");
		this.merchImage.setWidth("300px");
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

		this.merchButton.setStyleName("buttontest");		

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
			List<ItemCustomization> itemCustomizations = new ArrayList<ItemCustomization>();
			int itemQuantity = (this.qty.getValue() == null) ? 0 : this.qty.getValue().intValue();
			if(itemQuantity <= 0){
				UIUtils.alertUser(UIConstants.PURCHASE_TRANSACTION_MSG_QUANTITY);
			}else{
				for (int i=0;i < itemQuantity;i++){
					System.out.println("ADD");
					//set temporary customization IDs until item cart additions are finalized
					
					DefaultDao dao = new DefaultDao();
					
					ItemCustomization itemsCustTmp = new ItemCustomization();
					itemsCustTmp.setIcMtItemNum(this.merchTableItem.getMtItemNum());
					
					//dao.updateOrCreateEntity(itemsCustTmp, null, dao.getEntityManager());
					try {
						dao.updateOrCreateEntity(itemsCustTmp, null);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					System.out.println(String.format("ID==>%d", itemsCustTmp.getIcId() ));

					itemsCustTmp.setIcId(i);
					
					MerchTable merchTableItemTmp = new MerchTable();
					try {
						merchTableItemTmp =  this.merchTableItem.clone();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
	//				merchTableItemTmp.setMtIcId(i);
					merchTableItemTmp.setMtIcId(0);
					
					itemCustomizations.add(itemsCustTmp);
					itemsToBeAdded.add(merchTableItemTmp);
					
					this.qty.clear();
					Notification.show("Item added.");
				
				}
				
	//			logger.info("merchItemIsCustomizeable==>"+this.merchTableItem.isMtCustomizeable());
				System.out.println("merchItemIsCustomizeable==>"+this.merchTableItem.isMtCustomizeable());

				if(this.merchTableItem.isMtCustomizeable()) {
				
					ItemCustomizationWindow customWindow = new ItemCustomizationWindow(itemsToBeAdded,itemCustomizations);
					customWindow.setItemCustomizations(itemCustomizations);
					customWindow.setCustUpdateLister(this.getCartUpdateListener());
//					CartSingleton.getInstance().getCheckoutCart().setUpdateListener(this.getCartUpdateListener());
//					this.getCartUpdateListener().updateObject();
					UI.getCurrent().addWindow(customWindow);

				}else {
					System.out.println("ADD ITEM");
					System.out.println("itemsToBeAdded==>"+itemsToBeAdded.toString());			
	//				checkoutCart.addItemToCart(this.merchTableItem);
					checkoutCart.addItemsCart(itemsToBeAdded);

					//update cart item badge
					this.getCartUpdateListener().updateObject();
				}

			}//end if for qty check
			
		});
		
	}
	public UpdateListener getCartUpdateListener() {
		return cartUpdateListener;
	}

	public void setCartUpdateListener(UpdateListener cartUpdateListener) {
		this.cartUpdateListener = cartUpdateListener;
	}

}
