package com.vaadin.root.framework.views;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Base64;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UploadImagesView extends VerticalLayout implements View {

	BusinessInfo businessInfo;
	final Upload upload = new Upload();
	Button uploadButton = new Button("Save");
	
//	public AboutView(BusinessInfo bi) {
	public UploadImagesView() {
		super();
		setProperties();
		buildLayout();
	}
	
	private void buildLayout(){
		
		HorizontalLayout buttonPanel = new HorizontalLayout();
		
		this.uploadButton.setEnabled(false);

		this.upload.setButtonCaption("UPLOAD IMAGES");
		UploadReceiver receiver = new UploadReceiver();
		this.upload.setReceiver(receiver);
		this.upload.addSucceededListener(receiver);

		//upload.addChangeListener(createFilenameChangeListener());
		this.uploadButton = new Button("Save/Add Documents");
		this.uploadButton.setId("upload-save-add-btn");
		this.uploadButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		this.uploadButton.setIcon(FontAwesome.UPLOAD);
		
		buttonPanel.addComponents(this.upload);
		
		
		StandardHeaderLayout standardHeader = UIUtils.getStandardHeaderLayout(2L);
		addComponents(standardHeader, new Label("UPLOAD IMAGES"),buttonPanel);
	}
	
	private void setProperties(){
		setHeight("100%");
		addStyleName("scrollable");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}	
	
	private class UploadReceiver implements Receiver, SucceededListener, FailedListener {
		
		private ByteArrayOutputStream outStream;


		@Override
		public void uploadFailed(FailedEvent event) {
			// TODO Auto-generated method stub
			Notification.show("Upload FAILED!!");
			
		}

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			// TODO Auto-generated method stub
			byte[] fileContent = outStream.toByteArray();
			String encodedImageData = Base64.getEncoder().encodeToString(fileContent);
			
			Notification.show("encoded image data==>"+encodedImageData);
			System.out.println("encoded image data==>"+encodedImageData);
			
			//write to image data to db


			
		}

		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			// TODO Auto-generated method stub
			outStream = new ByteArrayOutputStream();
			return outStream;
		}
	}

}
