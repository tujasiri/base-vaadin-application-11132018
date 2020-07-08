package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vaadin.root.dao.DefaultDataService;


/**
 * The persistent class for the order_identification database table.
 * 
 */
@Entity
@Table(name="order_identification")
@NamedQuery(name="OrderIdentification.findAll", query="SELECT o FROM OrderIdentification o")
public class OrderIdentification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="or_id")
	private long orId;

	@Column(name="or_complete")
	private boolean orComplete;

//	private byte orComplete;

	public OrderIdentification() {
//			DefaultDataService.getInstance().updateOrCreateEntity(MerchTable.class, null).getClass();
	}

	public long getOrId() {
		return this.orId;
	}

	public void setOrId(long orId) {
		this.orId = orId;
	}

//	public byte getOrComplete() {
	public boolean getOrComplete() {
		return this.orComplete;
	}

//	public void setOrComplete(byte orComplete) {
	public void setOrComplete(boolean orComplete) {
		this.orComplete = orComplete;
	}

}