package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;
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

	private String display;

	private String value;

	//bi-directional many-to-one association to RColor
	@OneToMany(mappedBy="RMerchType")
	private List<RColor> RColors;

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

	public List<RColor> getRColors() {
		return this.RColors;
	}

	public void setRColors(List<RColor> RColors) {
		this.RColors = RColors;
	}

	public RColor addRColor(RColor RColor) {
		getRColors().add(RColor);
		RColor.setRMerchType(this);

		return RColor;
	}

	public RColor removeRColor(RColor RColor) {
		getRColors().remove(RColor);
		RColor.setRMerchType(null);

		return RColor;
	}

}