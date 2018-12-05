package com.vaadin.root;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.framework.MerchItemDetailLayout;
import com.vaadin.root.framework.MerchLayout;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class StandardMainScreen extends VerticalLayout {
	
	private VerticalLayout standardMainLayout = new VerticalLayout();
	private Panel standardMainPanel= new Panel();
	
	public StandardMainScreen(){
		super();
		setProperties();
		buildPage();
	}
	
	private void buildPage(){
		
		standardMainLayout.setSizeFull();
		standardMainLayout.setMargin(true);
		standardMainLayout.setSpacing(true);
		
	
		Label testLabel = new Label("Hello World!");
		
		GridLayout gridLayout = new GridLayout(4,2);
		
		gridLayout.setSizeFull();
		gridLayout.setMargin(true);
		gridLayout.setSpacing(true);
		
		List<MerchTable> mtList = new ArrayList<MerchTable>();
		List<MerchLayout> merchLayoutList = new ArrayList<MerchLayout>();
		
		for(int i=0;i<8;i++)
			mtList.add(DefaultDataService.getInstance().getMerchDao().findOneRecord(12));
		
		mtList.stream().forEach(x->{
		 merchLayoutList.add(new MerchLayout(x));
		});
		
		int idx =0;
		
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
				gridLayout.addComponent(merchLayoutList.get(idx++),j,i);
		
		
		standardMainLayout.addComponent(gridLayout);
		standardMainLayout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
		standardMainPanel.setContent(standardMainLayout);
		
//		addComponent(standardMainLayout);
		addComponent(standardMainPanel);
		
		
		
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
		setSizeFull();
		addStyleName("");
		
	}
}
