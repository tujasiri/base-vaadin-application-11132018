package com.vaadin.root.windows;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.root.framework.listeners.UpdateListener;
import com.vaadin.root.model.ItemCustomization;
import com.vaadin.root.model.RSize;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SelectCustomizationWindow extends Window {
	private ComboBox cbSize = new ComboBox("Select Size");
	private ComboBox cbColor = new ComboBox("Select Color");
	private ComboBox cbGender = new ComboBox("Select Size");
	private ItemCustomization itemCustom = new ItemCustomization();
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private Button updateButton = new Button("Update");
	private Button closeButton = new Button("Close");
	private UpdateListener updatelistener; 
	

	public SelectCustomizationWindow(){
		buildWindow();
	
		this.getUpdateButton().addClickListener(e->{
			String size = this.getCbSize().getValue() != null ? this.getCbSize().getValue().toString() : "";
			String color = this.getCbColor().getValue() != null ? this.getCbColor().getValue().toString() : "";
			String gender = this.getCbGender().getValue() != null ? this.getCbGender().getValue().toString() : "";

			this.getUpdatelistener().updateCustomizationRecord(size,color,gender);
		});
		
		this.getCloseButton().addClickListener(e->{
			this.close();
		});
	} 
	
	public void buildWindow(){
		this.setModal(true);
		setWidth(40.0f,Unit.PERCENTAGE);
		setHeight(40.0f,Unit.PERCENTAGE);
		cbSize.setDataProvider(getComboDataProvider());

		buttonLayout.addComponents(updateButton,closeButton);
		layout.addComponents(cbSize,cbColor,cbGender,buttonLayout);
		setContent(layout);
	}

	public Button getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(Button updateButton) {
		this.updateButton = updateButton;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(Button closeButton) {
		this.closeButton = closeButton;
	}

	public ItemCustomization getItemCustom() {
		return itemCustom;
	}

	public void setItemCustom(ItemCustomization itemCustom) {
		this.itemCustom = itemCustom;
	}

	public UpdateListener getUpdatelistener() {
		return updatelistener;
	}

	public ComboBox getCbSize() {
		return cbSize;
	}

	public void setCbSize(ComboBox cbSize) {
		this.cbSize = cbSize;
	}

	public ComboBox getCbColor() {
		return cbColor;
	}

	public void setCbColor(ComboBox cbColor) {
		this.cbColor = cbColor;
	}

	public ComboBox getCbGender() {
		return cbGender;
	}

	public void setCbGender(ComboBox cbGender) {
		this.cbGender = cbGender;
	}

	public void setUpdatelistener(UpdateListener updatelistener) {
		this.updatelistener = updatelistener;
	}
	
	private ListDataProvider<String> getComboDataProvider(){
		List<String> rSize = new ArrayList<>();
		
		rSize.add("S");
		rSize.add("M");
		rSize.add("L");
		
		ListDataProvider<String> cbDataProvider = new ListDataProvider<>(rSize);
		
		return cbDataProvider;
	}	
	
	
}
