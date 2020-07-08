package com.vaadin.root.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;


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
public class MerchTable implements Cloneable, Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mt_item_num")
	private int mtItemNum;
	
	@Column(name="mt_ic_id")
	private long mtIcId;

	@Lob
	@Column(name="mt_image")
	private byte[] mtImage;

	@Column(name="mt_image_id")
	private String mtImageId;

	@Column(name="mt_item_desc_long")
	private String mtItemDescLong;

	@Column(name="mt_item_desc_short")
	private String mtItemDescShort;

	@Column(name="mt_item_link")
	private String mtItemLink;

	@Column(name="mt_item_price")
	private float mtItemPrice;

	@Column(name="mt_item_type")
	private String mtItemType;

	@Column(name="mt_order_id")
	private long mtOrderId;

	@Lob
	@Column(name="mt_special_note")
	private String mtSpecialNote;

	@Column(name="mt_stock_qty")
	private short mtStockQty;

	@Column(name="mt_customizeable")
	private boolean mtCustomizeable;

	@Column(name="mt_or_seq")
	private long mtOrSeq;

	public MerchTable() {
	}

	public int getMtItemNum() {
		return this.mtItemNum;
	}

	public void setMtItemNum(int mtItemNum) {
		this.mtItemNum = mtItemNum;
	}

	public byte[] getMtImage() {
		return this.mtImage;
	}

	public void setMtImage(byte[] mtImage) {
		this.mtImage = mtImage;
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

	public long getMtOrderId() {
		return this.mtOrderId;
	}

	public void setMtOrderId(long mtOrderId) {
		this.mtOrderId = mtOrderId;
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

	public long getMtIcId() {
		return mtIcId;
	}

	public void setMtIcId(long mtIcId) {
		this.mtIcId = mtIcId;
	}
	

	public boolean isMtCustomizeable() {
		return mtCustomizeable;
	}

	public void setMtCustomizeable(boolean mtCustomizeable) {
		this.mtCustomizeable = mtCustomizeable;
	}

	@Override
	public MerchTable clone() throws CloneNotSupportedException {
		return (MerchTable)super.clone();
	}

	@Override
	public String toString() {
		return "MerchTable [mtItemNum=" + mtItemNum + ", mtIcId=" + mtIcId +/* ", mtImage=" + Arrays.toString(mtImage)

				+*/ ", mtImageId=" + mtImageId + ", mtItemDescLong=" + mtItemDescLong + ", mtItemDescShort="
				+ mtItemDescShort + ", mtItemLink=" + mtItemLink + ", mtItemPrice=" + mtItemPrice + ", mtItemType="
				+ mtItemType + ", mtOrderId=" + mtOrderId + ", mtSpecialNote=" + mtSpecialNote + ", mtStockQty="
				+ mtStockQty + "]\n\n";
	}

	public long getMtOrSeq() {
		return mtOrSeq;
	}

	public void setMtOrIdSeq(long mtOrSeq) {
		this.mtOrSeq = mtOrSeq;
	}
	
	
	

}