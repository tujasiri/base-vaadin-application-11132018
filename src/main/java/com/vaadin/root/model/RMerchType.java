package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vaadin.root.enums.MerchTypes;

import java.util.List;


/**
 * The persistent class for the R_MERCH_TYPES database table.
 * 
 */
@Entity
@Table(name="R_MERCH_TYPES")
@NamedQuery(name="RMerchType.findAll", query="SELECT r FROM RMerchType r")
public class RMerchType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	

	@Column(name="display")
	private String display;

	@Column(name="value")
	private String value;
	
	@Enumerated(EnumType.STRING)
	@Column(name="merch_type")
    private MerchTypes merch_type;

//	bi-directional many-to-one association to RColor
//	@OneToMany(mappedBy="value")
//	@OneToMany(mappedBy="RMerchType")
//	private List<RColor> RColors;

	public RMerchType() {
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

//	public List<RColor> getRColors() {
//		return this.RColors;
//	}
//
//	public void setRColors(List<RColor> RColors) {
//		this.RColors = RColors;
//	}

//	public RColor addRColor(RColor RColor) {
//		getRColors().add(RColor);
//		RColor.setRMerchType(this);
////		RColor.setValue(this);
//
//		return RColor;
//	}
//
//	public RColor removeRColor(RColor RColor) {
//		getRColors().remove(RColor);
//		RColor.setRMerchType(null);
////		RColor.setValue(null);
//
//		return RColor;
//	}

	public MerchTypes getMerch_type() {

		System.out.println("merchType==>"+this.merch_type);
		return this.merch_type;
	}

	public void setMerch_type(MerchTypes merch_type) {
		System.out.println("merchType==>"+this.merch_type);
		this.merch_type = merch_type;
	}

}