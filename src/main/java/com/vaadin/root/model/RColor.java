package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vaadin.root.enums.MerchTypes;


/**
 * The persistent class for the R_COLORS database table.
 * 
 */
@Entity
@Table(name="R_COLORS")
@NamedQuery(name="RColor.findAll", query="SELECT r FROM RColor r")
public class RColor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String display;

	private String value;

	//bi-directional many-to-one association to RMerchType
//	@ManyToOne
//	@JoinColumn(name="merch_type")
	@Enumerated(EnumType.STRING)
	@Column(name="merch_type")
    private MerchTypes merch_type;

//	@Column(name="merch_type")
//	private RMerchType RMerchType;
//	private RMerchType merch_type;
//	private String RMerchType;

	public RColor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

//	public RMerchType getValue() {
//		return value;
//	}
//
//	public void setValue(RMerchType value) {
//		this.value = value;
//	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public MerchTypes getMerch_type() {
		return merch_type;
	}

	public void setMerch_type(MerchTypes merch_type) {
		this.merch_type = merch_type;
	}


//	public RMerchType getRMerchType() {
//		return this.RMerchType;
//	}
//
//		public void setRMerchType(RMerchType RMerchType) {
//		this.RMerchType = RMerchType;
//	}
	
//	public String getRMerchType() {
//		return this.RMerchType;
//	}
//
//	public void setRMerchType(RMerchType String) {
//		this.RMerchType = RMerchType;
//	}

}