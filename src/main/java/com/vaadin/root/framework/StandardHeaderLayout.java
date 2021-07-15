package com.vaadin.root.framework;

import com.vaadin.client.ui.Icon;
import com.vaadin.client.ui.menubar.MenuItem;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.dto.CheckoutCart;
import com.vaadin.root.framework.listeners.UpdateListener;
import com.vaadin.root.jscomponent.TimerComponent;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.root.windows.ShoppingCartWindow;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class StandardHeaderLayout extends CssLayout{
	
	MenuBar standardMenu = new MenuBar();
	TimerComponent tc = new TimerComponent();
	VerticalLayout infoContentLayout = new VerticalLayout();
	VerticalLayout mainContentLayout = new VerticalLayout();
	Image businessLogo = new Image();
	Image headerBanner = new Image();
	BusinessInfo businessInfo = new BusinessInfo();
	UpdateListener headerUpdateListener = new UpdateListener(){

		@Override
		public void updateRecord() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCustomizationRecord(String size, String color, String gender) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateObject() {
				refresh();
		}
		
	};

	Label cartLabel = new Label();
	
//	Image businessOverlay = new Image();
	
	

	public StandardHeaderLayout(BusinessInfo bi){
			super();
			this.businessInfo = bi;
			this.businessLogo = UIUtils.byteArrayToImage(this.businessInfo.getBiLogo());
			this.headerBanner = UIUtils.byteArrayToImage(this.businessInfo.getBiHeader());
			buildLayout();
	}
	
	public void buildLayout(){
		tc.addStyleName("zeroheightwidth");
		setSizeFull();
		Label testLabel = new Label("menuitem");
		
		testLabel.addContextClickListener(e->{
			tc.openmenu();
		});
		
		addStyleName("headerLayoutStyle");
		addStyleName("outerdiv");
		addStyleName("displayblock");
		
		addStyleName("headertestborder");
		
		Button button = new Button("Menu click");
		
		button.addClickListener(e ->{
			tc.openmenu();
		});
		
		standardMenu = buildMenuBar();
		standardMenu.addStyleName("headermenu");
		
//		standardMenu.setSizeUndefined();
		
		HorizontalLayout actionLayout = buildActionLayout();
		VerticalLayout infoLayout = buildInfoLayout();
		
//		infoContentLayout.addComponents(buildInfoLayout(), buildActionLayout());
		infoContentLayout.addComponents(infoLayout, actionLayout);
		
//		infoContentLayout.setComponentAlignment(infoLayout, Alignment.BOTTOM_RIGHT);
//		infoContentLayout.setComponentAlignment(actionLayout, Alignment.BOTTOM_RIGHT);
		infoContentLayout.addStyleName("rightjustify");
		infoContentLayout.setWidth("350px");
		infoContentLayout.addStyleName("headerchildbackground");
		
		Label headerLabel = new Label("HEADER PANEL LOGOX");
		headerLabel.addStyleName("headerlogo");
		
		tc.addStyleName("testborder");
		tc.addStyleName("zeroheightwidth");
		
//		mainContentLayout.addComponents(headerLabel, tc, standardMenu);
		this.businessLogo.addStyleName("headerlogo");
		this.businessLogo.addStyleName("headerchildbackground");
		mainContentLayout.addComponents(this.businessLogo, tc, standardMenu);
		mainContentLayout.setWidth("350px");
		mainContentLayout.addStyleName("testborder");
//		mainContentLayout.addStyleName("recede");
		
		
		addComponents(tc, this.businessLogo, standardMenu, infoContentLayout);
//		addComponents(this.headerBanner, this.businessLogo, standardMenu, infoContentLayout);
		addStyleName("headerbackground");
//		addComponents(infoContentLayout, mainContentLayout);
		
//		this.setComponentAlignment(headerLabel, Alignment.BOTTOM_LEFT);
//		this.setComponentAlignment(standardMenu, Alignment.BOTTOM_RIGHT);
		//this.setComponentAlignment(infoContentLayout, Alignment.BOTTOM_RIGHT);
		
	}
	
	private HorizontalLayout buildActionLayout(){
		HorizontalLayout actionLayout = new HorizontalLayout();
		HorizontalLayout cartLayout = new HorizontalLayout();
//		Label cartLabel = new Label();
//		cartLabel.setIcon(VaadinIcons.CART);

		this.cartLabel.setCaptionAsHtml(true);
		
		this.cartLabel.setCaption(String.format("<div class=\"carticon\"><span class=\"icon\">"
				+ "</span><span class=\"badge\">%d</span></div>",CartSingleton.getInstance().getCheckoutCart().itemsInCart().size()));
		
//		CheckoutCart cc = (CheckoutCart)VaadinSession.getCurrent().getAttribute("shoppingcart");
//		
//
//		this.cartLabel.setCaption(String.format("<div class=\"carticon\"><span class=\"icon\">"
//				+ "</span><span class=\"badge\">%d</span></div>",cc.itemsInCart().size()));
//				
		cartLayout.addComponent(this.cartLabel);
		
		cartLayout.addLayoutClickListener(e->{ 
//			tc.alertme();
			tc.getdimensions();
		
			System.out.println(String.format("screen height ==> %d\n screen width ==> %d\n", tc.getScreenHeight(),tc.getScreenWidth()));
			System.out.println("ITEMS IN CART==>"+CartSingleton.getInstance().getCheckoutCart().toString());
			UI.getCurrent().addWindow(new ShoppingCartWindow());
			
			//using Observer Pattern to update cart badge as items are added or deleted from cart
			CartSingleton.getInstance().getCheckoutCart().setUpdateListener(this.headerUpdateListener);

		});
		cartLayout.addStyleName("pointerCursor");
		
		
	
		
		Label testLabel = new Label("1");
		
//		actionLayout.setWidth(25.0f, Unit.PERCENTAGE);
		TextField cartText = new TextField("");
		cartText = new TextField("");
		cartText.setIcon(VaadinIcons.CART);
		


		//testLabel.setId("carticon");
		testLabel.addStyleName("badge");

		
		actionLayout.addComponents(cartLayout);
		//actionLayout.addComponents(cartLayout, cartText);
		
		actionLayout.setWidth("100px");
//		actionLayout.addStyleName("testborder");
		//***add shopping icon
	
		return actionLayout;
	}
	
	private VerticalLayout buildInfoLayout(){
		VerticalLayout infoLayout = new VerticalLayout();
//		infoLayout.setWidth(25.0f, Unit.PERCENTAGE);
		infoLayout.setWidth("350px");
		infoLayout.addStyleName("testborder");
		//***add business info 
		

//		infoLayout.addComponents( new Label("BUSINESS INFO 1"), new Label("BUSINESS INFO 2"), new Label("BUSINESS INFO 3"));
		infoLayout.addComponents( new Label(this.businessInfo.getBiName()), 
									new Label(this.businessInfo.getBiAddress()), 
									new Label(this.businessInfo.getBiAddress2() != null ?
											this.businessInfo.getBiAddress2():""),
									new Label(this.businessInfo.getBiCity() +", "+
											this.businessInfo.getBiState() + ", "+ this.businessInfo.getBiZip()));
											
		return infoLayout;
	}
	
	
	private MenuBar buildMenuBar(){
	
		//MenuBar menubar = new MenuBar();
		//final Command command = selectedItem -> Notification.show("Action " + selectedItem.getText(), Type.TRAY_NOTIFICATION);
		final Command command = selectedItem ->{ 
				System.out.println("selectedItem ==>"+selectedItem.getText());
				UI.getCurrent().getNavigator().navigateTo("about");
			};
		 
		final Command goToHome = selectedItem ->{ 
				System.out.println("selectedItem ==>"+selectedItem.getText());
				UI.getCurrent().getNavigator().navigateTo("home");
			};
		 
		final Command goToUploadImages = selectedItem ->{ 
				System.out.println("selectedItem ==>"+selectedItem.getText());
				UI.getCurrent().getNavigator().navigateTo("upload_images");
			};
		 
		        MenuBar sample = new MenuBar();
		        sample.setWidth(100.0f, Unit.PERCENTAGE);
		        
		        //HOME top-level item
		        com.vaadin.ui.MenuBar.MenuItem homeItem = sample.addItem("HOME", null, goToHome);
		        
		        // Another top-level item
		        com.vaadin.ui.MenuBar.MenuItem colds = sample.addItem("Cold", null, null);
		        colds.addItem("Home",      null, goToHome);
		        colds.addItem("Weissbier", null, command);
		        
		        // Another submenu item with a sub-submenu
		        com.vaadin.ui.MenuBar.MenuItem sub = colds.addItem("Upload Images", null, null);
		        colds.addItem("UploadImages",      null, goToUploadImages);
		        colds.addItem("Sub2", null, command);

		        // Another top-level item
		        com.vaadin.ui.MenuBar.MenuItem snacks = sample.addItem("Snacks", null, null);
		        snacks.addItem("Weisswurst", null, command);
		        snacks.addItem("Bratwurst",  null, command);
		        snacks.addItem("Currywurst", null, command);
		        
		        // Another top-level itemd
		        com.vaadin.ui.MenuBar.MenuItem almostlast = sample.addItem("Dummy Item", null, null);
		        almostlast.addItem("Dummy Node",      null, command);
		        
		        // Another top-level itemd
		        com.vaadin.ui.MenuBar.MenuItem last = sample.addItem("Dummy Dummy Item", null, null);
		        last.addItem("Dummy Node",      null, command);
		
		
		return sample; 
		
	}
	
	public void refresh(){
		this.cartLabel.setCaption(String.format("<div class=\"carticon\"><span class=\"icon\">"
				+ "</span><span class=\"badge\">%d</span></div>",CartSingleton.getInstance().getCheckoutCart().itemsInCart().size()));
	}
	
	public UpdateListener getHeaderUpdateListener() {
		return headerUpdateListener;
	}

	public void setHeaderUpdateListener(UpdateListener headerUpdateListener) {
		this.headerUpdateListener = headerUpdateListener;
	}

}
