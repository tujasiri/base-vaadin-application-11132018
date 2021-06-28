package com.vaadin.root.framework.views;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.root.dao.Dao;
import com.vaadin.root.dao.DefaultDao;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.ViewerImage;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
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
	
	ComboBox<MerchTable> merchItemCombo = new ComboBox<MerchTable>();
	
	
//	public AboutView(BusinessInfo bi) {
	public UploadImagesView() {
		super();
		setProperties();
		buildLayout();
	}
	
	private void buildLayout(){
		this.merchItemCombo.addValueChangeListener(v->{
			System.out.println("VALUE CHANGED");
			System.out.println("merchitem==>"+this.merchItemCombo.getValue().getMtItemNum());
		});
		
		this.merchItemCombo.setWidth("512px");
		
		this.merchItemCombo.setItems(this.getMerchItemList());
		this.merchItemCombo.setItemCaptionGenerator(p->p.getMtItemNumAsString() + "::" + p.getMtItemDescShort());
		
		HorizontalLayout buttonPanel = new HorizontalLayout();
		
		this.upload.addAttachListener(x->{
		});
		
		this.uploadButton.addClickListener(b->{
		});
		
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
		addComponents(standardHeader, new Label("UPLOAD IMAGES"),buttonPanel, merchItemCombo);
	}
	
	private void setProperties(){
		setHeight("100%");
		addStyleName("scrollable");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}	
	
	public List<MerchTable> getMerchItemList(){
    	return DefaultDataService.getInstance().getMerchDao().findAll();
	}
	
	public void writeUploadImageToDB(int viMtItemNum, String viBase64Str){
		ViewerImage vImg = new ViewerImage();
		DefaultDao dao = new DefaultDao();
		
		vImg.setViBase64Str(viBase64Str);
		vImg.setViMtItemNum(viMtItemNum);
		dao.updateOrCreateEntity(vImg,null);
	}
	
	private class UploadReceiver implements Receiver, SucceededListener, FailedListener {
		
		private ByteArrayOutputStream outStream;

		@Override
		public void uploadFailed(FailedEvent event) {
			Notification.show("Upload FAILED!!");
		}

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			byte[] fileContent = outStream.toByteArray();
			String encodedImageData = Base64.getEncoder().encodeToString(fileContent);
			writeUploadImageToDB(merchItemCombo.getValue().getMtItemNum(), encodedImageData);
		}

		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			outStream = new ByteArrayOutputStream();
			return outStream;
		}
	}

}
