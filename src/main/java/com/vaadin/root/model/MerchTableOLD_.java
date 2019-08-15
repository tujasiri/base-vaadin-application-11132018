package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vaadin.root.dto.Customizations;

import java.sql.Blob;
import java.util.Arrays;
import java.util.List;


/**
 * The persistent class for the merch_table database table.
 * 
 */
@Entity
@Table(name="merch_table")
@NamedQueries(value ={ 
		@NamedQuery(name="MerchTable.findAll", query="SELECT m FROM MerchTable m"),
		@NamedQuery(name="MerchTable.findOneRecord", query="SELECT m FROM MerchTable m where m.mtItemNum = :idx")
})
public class MerchTableOLD_ implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private List<Customizations> itemCustomizations;

	@Column(name="mt_image_id")
	private String mtImageId;

	@Column(name="mt_item_desc_long")
	private String mtItemDescLong;

	@Column(name="mt_item_desc_short")
	private String mtItemDescShort;

	@Column(name="mt_item_link")
	private String mtItemLink;
	
	@Id
	@Column(name="mt_item_num")
	private int mtItemNum;

	@Column(name="mt_item_price")
	private float mtItemPrice;

	@Column(name="mt_item_type")
	private String mtItemType;

	@Lob
	@Column(name="mt_special_note")
	private String mtSpecialNote;

	@Column(name="mt_stock_qty")
	private short mtStockQty;

	@Lob
	@Column(name="mt_image")
	private byte[] mtImage;

	public MerchTableOLD_() {
	}

	public String getMtImageId() {
		return this.mtImageId;
	}

	public void setMtImageId(String mtImageId) {
		this.mtImageId = mtImageId;
	}

	public String getMtItemDescLong() {
		return this.mtItemDescLong;
	}

	public void setMtItemDescLong(String mtItemDescLong) {
		this.mtItemDescLong = mtItemDescLong;
	}

	public String getMtItemDescShort() {
		return this.mtItemDescShort;
	}

	public void setMtItemDescShort(String mtItemDescShort) {
		this.mtItemDescShort = mtItemDescShort;
	}

	public String getMtItemLink() {
		return this.mtItemLink;
	}

	public void setMtItemLink(String mtItemLink) {
		this.mtItemLink = mtItemLink;
	}

	public int getMtItemNum() {
		return this.mtItemNum;
	}

	public void setMtItemNum(int mtItemNum) {
		this.mtItemNum = mtItemNum;
	}

	public float getMtItemPrice() {
		return this.mtItemPrice;
	}

	public void setMtItemPrice(float mtItemPrice) {
		this.mtItemPrice = mtItemPrice;
	}

	public String getMtItemType() {
		return this.mtItemType;
	}

	public void setMtItemType(String mtItemType) {
		this.mtItemType = mtItemType;
	}

	public String getMtSpecialNote() {
		return this.mtSpecialNote;
	}

	public void setMtSpecialNote(String mtSpecialNote) {
		this.mtSpecialNote = mtSpecialNote;
	}

	public short getMtStockQty() {
		return this.mtStockQty;
	}

	public void setMtStockQty(short mtStockQty) {
		this.mtStockQty = mtStockQty;
	}
	
	public byte[] getMtImage() {
		return mtImage;
	}

	public void setMtImage(byte[] mtImage) {
		this.mtImage = mtImage;
	}

	@Override
	public String toString() {
		return "MerchTable [itemCustomizations=" + itemCustomizations + ", mtImageId=" + mtImageId + ", mtItemDescLong="
				+ mtItemDescLong + ", mtItemDescShort=" + mtItemDescShort + ", mtItemLink=" + mtItemLink
				+ ", mtItemNum=" + mtItemNum + ", mtItemPrice=" + mtItemPrice + ", mtItemType=" + mtItemType
				+ ", mtSpecialNote=" + mtSpecialNote + ", mtStockQty=" + mtStockQty + ", mtImage="
				+ Arrays.toString(mtImage) + "]";
	}
	

}