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
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View {
	
	private VerticalLayout standardMainLayout = new VerticalLayout();
	private HorizontalLayout subLayout = new HorizontalLayout();
	private HorizontalLayout hLayout = new HorizontalLayout();
	private Panel standardMainPanel= new Panel();
	private StandardHeaderLayout headerLayout;
//		this.businessInfo = DefaultDataService.getInstance().getBusinessInfoDao().findById(2L);
	private StandardSideLayout sideLayout = new StandardSideLayout();
	
	public HomeView(){
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
	
		Label contentLabel = new Label(this.getContentLabel());
        contentLabel.setContentMode(ContentMode.HTML);
		
		this.headerLayout.addStyleName("headerLayoutStyle");
		
		SpacerLabel spacer = new SpacerLabel();
		spacer.addStyleName("testborder");
		standardMainLayout.addComponents(this.headerLayout,spacer,contentLabel);
//		standardMainLayout.addComponents(spacer,contentLabel);
		
//		standardMainLayout.addComponents(this.headerLayout, this.subLayout);
//		standardMainLayout.setExpandRatio(this.subLayout, 1);
//		standardMainLayout.setExpandRatio(this.subLayout, 1);
//		standardMainLayout.setExpandRatio(this.headerLayout, 1);
		
		
		standardMainLayout.setComponentAlignment(contentLabel, Alignment.MIDDLE_CENTER);
		
		standardMainPanel.setContent(standardMainLayout);
		
		standardMainLayout.addStyleName("scrollable");;
		
		addComponents(standardMainLayout);
		setExpandRatio(standardMainLayout,1.0f);
		
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
	
	public String getContentLabel() {
		
		return "\n" + 
				"\n" + 
				"\n" + 
				"<div id=\"main_blog_container\">\n" + 
				"\n" + 
				"	<div class=\"home_blog_entry\">\n" + 
				"		<div class=\"home_blog_entry_content\">\n" + 
				"			<table class=\"dining_room_table\">\n" + 
				"            	<tr class=\"home_blog_table_row\"><td><h1>THE RULER'S BACK!</h1></td></tr>\n" + 
				"				<tr>\n" + 
				"				<td><img class=\"home_blog_table_cell\" src=\"data:image/jpeg;base64,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\"></td>\n" + 
				"                <td><p>eMCee Truth Universal returns with \"The Ruler's Back\" b/w \"Conscious x Trill\"</p></td>\n" + 
				"				</tr>\n" + 
				"			</table>\n" + 
				"		</div><!--end home_blog_entry_content -->\n" + 
				"	\n" + 
				"	</div><!--end home_blog_entry -->\n" + 
				"\n" + 
				"</div><!--end main_blog_container -->";
		
	}
	
	
}
