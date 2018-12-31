package com.vaadin.root.framework;

import com.vaadin.client.ui.menubar.MenuItem;
import com.vaadin.root.jscomponent.TimerComponent;
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
import com.vaadin.ui.VerticalLayout;

public class StandardHeaderLayout extends CssLayout{
	
	MenuBar standardMenu = new MenuBar();
	TimerComponent tc = new TimerComponent();
	VerticalLayout infoContentLayout = new VerticalLayout();
//	Image businessOverlay = new Image();
	
	public StandardHeaderLayout(/*BusinessInfo Class*/){
			super();
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
		
		Button button = new Button("Menu click");
		
		button.addClickListener(e ->{
			tc.openmenu();
		});
		
		standardMenu = buildMenuBar();
//		standardMenu.setSizeUndefined();
		
		HorizontalLayout actionLayout = buildActionLayout();
		VerticalLayout infoLayout = buildInfoLayout();
		
//		infoContentLayout.addComponents(buildInfoLayout(), buildActionLayout());
		infoContentLayout.addComponents(infoLayout, actionLayout);
		
//		infoContentLayout.setComponentAlignment(infoLayout, Alignment.BOTTOM_RIGHT);
//		infoContentLayout.setComponentAlignment(actionLayout, Alignment.BOTTOM_RIGHT);
		infoContentLayout.addStyleName("rightjustify");
//		infoContentLayout.setWidth("400px");
		
		Label headerLabel = new Label("HEADER PANEL LOGO");
		
//		addComponents(tc, headerLabel, standardMenu, infoContentLayout);
		addComponents(headerLabel, standardMenu, infoContentLayout);
		
//		this.setComponentAlignment(headerLabel, Alignment.BOTTOM_LEFT);
//		this.setComponentAlignment(standardMenu, Alignment.BOTTOM_RIGHT);
		//this.setComponentAlignment(infoContentLayout, Alignment.BOTTOM_RIGHT);
		
	}
	
	private HorizontalLayout buildActionLayout(){
		HorizontalLayout actionLayout = new HorizontalLayout();
//		actionLayout.setWidth(25.0f, Unit.PERCENTAGE);
		actionLayout.setWidth("100px");
		actionLayout.addStyleName("testborder");
		//***add shopping icon
		
		return actionLayout;
	}
	
	private VerticalLayout buildInfoLayout(){
		VerticalLayout infoLayout = new VerticalLayout();
//		infoLayout.setWidth(25.0f, Unit.PERCENTAGE);
		infoLayout.setWidth("400px");
		infoLayout.addStyleName("testborder");
		//***add business info 
		infoLayout.addComponents( new Label("BUSINESS INFO 1"), new Label("BUSINESS INFO 2"), new Label("BUSINESS INFO 3"));
		return infoLayout;
	}
	
	
	private MenuBar buildMenuBar(){
	
		//MenuBar menubar = new MenuBar();
		final Command command = selectedItem -> Notification.show("Action " + selectedItem.getText(), Type.TRAY_NOTIFICATION);
		 
		 
		        MenuBar sample = new MenuBar();
		        sample.setWidth(100.0f, Unit.PERCENTAGE);
		        
		        // Another top-level item
		        com.vaadin.ui.MenuBar.MenuItem colds = sample.addItem("Cold", null, null);
		        colds.addItem("Milk",      null, command);
		        colds.addItem("Weissbier", null, command);
		        
		        // Another submenu item with a sub-submenu
		        com.vaadin.ui.MenuBar.MenuItem sub = colds.addItem("Cold", null, null);
		        colds.addItem("Sub1",      null, command);
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

}
