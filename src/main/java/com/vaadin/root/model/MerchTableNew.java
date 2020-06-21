package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the merch_table database table.
 * 
 */
@Entity
@Table(name="merch_table")
@NamedQuery(name="MerchTableNew.findAll", query="SELECT m FROM MerchTableNew m")
public class MerchTableNew implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mt_item_num")
	private int mtItemNum;

	@Column(name="mt_ic_id")
	private int mtIcId;

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
	private int mtOrderId;

	@Lob
	@Column(name="mt_special_note")
	private String mtSpecialNote;

	@Column(name="mt_stock_qty")
	private short mtStockQty;

	public MerchTableNew() {
	}

	public int getMtItemNum() {
		return this.mtItemNum;
	}

	public void setMtItemNum(int mtItemNum) {
		this.mtItemNum = mtItemNum;
	}

	public int getMtIcId() {
		return this.mtIcId;
	}

	public void setMtIcId(int mtIcId) {
		this.mtIcId = mtIcId;
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

	public int getMtOrderId() {
		return this.mtOrderId;
	}

	public void setMtOrderId(int mtOrderId) {
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

}