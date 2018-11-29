package com.vaadin.root;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class StandardMainScreen extends VerticalLayout {
	
	public StandardMainScreen(){
		super();
		setProperties();
		buildPage();
	}
	
	private void buildPage(){
		Label testLabel = new Label("Hello World!");
		GridLayout gridLayout = new GridLayout();
		
		try {
	
			byte[] bytearray = DefaultDataService.getInstance().getMerchDao().findOneRecord(12).getMtImage();
			
			Image img = UIUtils.byteArrayToImage(bytearray);
			
			img.setHeight(20.0f, Unit.PERCENTAGE);
			img.setWidth(15.0f, Unit.PERCENTAGE);
			
			addComponents(img);
			
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
		setSizeFull();
		addStyleName("");
		
	}
}
