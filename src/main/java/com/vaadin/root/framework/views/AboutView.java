package com.vaadin.root.framework.views;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.framework.MerchItemDetailLayout;
import com.vaadin.root.framework.MerchLayout;
import com.vaadin.root.framework.SpacerLabel;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.framework.StandardSideLayout;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class AboutView extends VerticalLayout implements View {
	
	private VerticalLayout standardMainLayout = new VerticalLayout();
	private HorizontalLayout subLayout = new HorizontalLayout();
	private HorizontalLayout hLayout = new HorizontalLayout();
	private Panel standardMainPanel= new Panel();
	private StandardHeaderLayout headerLayout;
//		this.businessInfo = DefaultDataService.getInstance().getBusinessInfoDao().findById(2L);
	private StandardSideLayout sideLayout = new StandardSideLayout();
	
	public AboutView(){
		super();
//		this.headerLayout = new StandardHeaderLayout( DefaultDataService.getInstance().getBusinessInfoDao().findById(2L));
		this.headerLayout = UIUtils.getStandardHeaderLayout(2L);
		setProperties();
		buildPage();
		addStyleName("scrollable");
	}
	
	private void buildPage(){
		
		VerticalLayout vl = new VerticalLayout();
		HorizontalLayout hl = new HorizontalLayout();
		
//		standardMainLayout.setSizeFull();
		standardMainLayout.setSizeUndefined();
		
		standardMainLayout.setMargin(true);
		standardMainLayout.setSpacing(true);
		
//		standardMainPanel.setSizeFull();
		standardMainPanel.setHeight("500px");
		standardMainPanel.setWidth("800px");
	
		Label testLabel = new Label("Hello World!");
		
		GridLayout gridLayout = new GridLayout(4,3);
		
		gridLayout.setSizeFull();
		gridLayout.setMargin(true);
		gridLayout.setSpacing(true);
		
//		vl.setSizeFull();
		vl.setHeight("1000px");
		vl.setWidth("1000px");
		vl.setMargin(true);
		vl.setSpacing(true);
		
//		for(int x=0;x<1000;x++)
//			vl.addComponent(new Label("Label "+x));
//		
//		for(int y=0;y<1000;y++)
//			hl.addComponent(new Label("Label "+y));
		
		List<MerchTable> mtList = new ArrayList<MerchTable>();
		List<MerchLayout> merchLayoutList = new ArrayList<MerchLayout>();
		
		for(int i=0;i<12;i++)
			mtList.add(DefaultDataService.getInstance().getMerchDao().findOneRecord(12));
		
		mtList.stream().forEach(x->{
		 merchLayoutList.add(new MerchLayout(x));
		});
		
		int idx=0;
		
		for(int i=0;i<3;i++){
			gridLayout.setRowExpandRatio(i,1.0f);
			for(int j=0;j<4;j++){
				gridLayout.setColumnExpandRatio(j,1.0f);
				MerchLayout ml = merchLayoutList.get(idx++);
				ml.setHeightUndefined();
//				ml.setWidth(10.0f,Unit.PERCENTAGE);
				ml.addStyleName("merchLayout");
				gridLayout.addComponent(ml,j,i);
//				gridLayout.addComponent(merchLayoutList.get(idx++),j,i);
			}
		}
		
		gridLayout.setSizeFull();
		this.subLayout.setSizeFull();
		
//		this.subLayout.addComponents(gridLayout, this.sideLayout);
		this.headerLayout.addStyleName("headerLayoutStyle");
		
		
//		standardMainLayout.addComponents(this.headerLayout, hl, vl);
//		standardMainLayout.addComponents(this.headerLayout, gridLayout);
		standardMainLayout.addComponents(this.headerLayout);
		SpacerLabel spacer = new SpacerLabel();
		spacer.addStyleName("testborder");
		standardMainLayout.addComponents(spacer);
		
//		standardMainLayout.addComponents(this.headerLayout, this.subLayout);
//		standardMainLayout.setExpandRatio(this.subLayout, 1);
//		standardMainLayout.setExpandRatio(this.subLayout, 1);
//		standardMainLayout.setExpandRatio(this.headerLayout, 1);
		
//		standardMainLayout.setExpandRatio(gridLayout, 1);
		
//		standardMainLayout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
		
		standardMainPanel.setContent(standardMainLayout);
		
//		addComponent(standardMainLayout);
//		this.headerLayout.setHeight(5.0f, Unit.PERCENTAGE);
//		addComponents(this.headerLayout, standardMainPanel);
//		addComponents(this.hLayout, standardMainPanel);
//		addComponents(standardMainPanel);
		
		
		standardMainLayout.addStyleName("scrollable");;
		
//		hLayout.addComponent(new Label("HEADER PANEL"));
//		hLayout.setSizeFull();
//		hLayout.addStyleName("testborder");

		
		addComponents(standardMainLayout);
		setExpandRatio(standardMainLayout,1.0f);
		
		
		
		try {
	
//			byte[] bytearray = DefaultDataService.getInstance().getMerchDao().findOneRecord(12).getMtImage();
//			
//			Image img = UIUtils.byteArrayToImage(bytearray);
//			
//			img.setHeight(20.0f, Unit.PERCENTAGE);
//			img.setWidth(15.0f, Unit.PERCENTAGE);
//			
//			addComponents(img);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private Image byteArrayToImage(final byte[]byteArray){
		StreamSource streamSource = new StreamResource.StreamSource(){

			@Override
			public InputStream getStream() {
				// TODO Auto-generated method stub
				return new ByteArrayInputStream(byteArray);
			}
		}; 
		
		return new Image(null, new StreamResource(streamSource,""));
		
	}

	private void setProperties(){
//		setSizeFull();
		setHeight("100%");
		addStyleName("");
		
	}

	public VerticalLayout getStandardMainLayout() {
		return standardMainLayout;
	}

	public void setStandardMainLayout(VerticalLayout standardMainLayout) {
		this.standardMainLayout = standardMainLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
}
