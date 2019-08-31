package com.vaadin.root.framework.listeners;

import java.util.List;

import com.vaadin.ui.Button.ClickListener;

/**
 * A simple listener class that gets notified when something is updated.
 * 
 * @author tujasiri`
 * @created 08.14.2019 
 */

public interface UpdateListener {
	public void updateRecord();
	public void updateCustomizationRecord(String size,String color,String gender);

}
