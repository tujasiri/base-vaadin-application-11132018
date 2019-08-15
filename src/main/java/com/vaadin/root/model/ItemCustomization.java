package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_customizations database table.
 * 
 */
@Entity
@Table(name="item_customizations")
@NamedQuery(name="ItemCustomization.findAll", query="SELECT i FROM ItemCustomization i")
public class ItemCustomization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ic_id")
	private int icId;

	@Column(name="ic_color")
	private String icColor;

	@Column(name="ic_gender")
	private String icGender;

	@Column(name="ic_mt_item_num")
	private int icMtItemNum;

	@Column(name="ic_size")
	private String icSize;

	public ItemCustomization() {
	}

	public int getIcId() {
		return this.icId;
	}

	public void setIcId(int icId) {
		this.icId = icId;
	}

	public String getIcColor() {
		return this.icColor;
	}

	public void setIcColor(String icColor) {
		this.icColor = icColor;
	}

	public String getIcGender() {
		return this.icGender;
	}

	public void setIcGender(String icGender) {
		this.icGender = icGender;
	}

	public int getIcMtItemNum() {
		return this.icMtItemNum;
	}

	public void setIcMtItemNum(int icMtItemNum) {
		this.icMtItemNum = icMtItemNum;
	}

	public String getIcSize() {
		return this.icSize;
	}

	public void setIcSize(String icSize) {
		this.icSize = icSize;
	}

}