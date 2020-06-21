package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the order_summary database view.
 * 
 */
@Entity
@Table(name="order_summary")
@NamedQueries(value = { 
@NamedQuery(name="OrderSummary.findAll", query="SELECT o FROM OrderSummary o"),
@NamedQuery(name="OrderSummary.findOrderRecord", query="SELECT o FROM OrderSummary o WHERE o.orId= :orid")})

public class OrderSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="or_seq")
	private long orSeq;

	@Column(name="ic_color")
	private String icColor;

	@Column(name="ic_gender")
	private String icGender;

	@Column(name="ic_id")
	private int icId;

	@Column(name="ic_mt_item_num")
	private int icMtItemNum;

	@Column(name="ic_size")
	private String icSize;

	@Column(name="mt_customizeable")
	private boolean mtCustomizeable;

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

	@Column(name="mt_item_num")
	private int mtItemNum;

	@Column(name="mt_item_price")
	private float mtItemPrice;

	@Column(name="mt_item_type")
	private String mtItemType;

	@Column(name="mt_or_seq")
	private long mtOrSeq;

	@Column(name="mt_order_id")
	private int mtOrderId;

	@Lob
	@Column(name="mt_special_note")
	private String mtSpecialNote;

	@Column(name="mt_stock_qty")
	private short mtStockQty;

	@Column(name="or_cb_id")
	private int orCbId;

	@Temporal(TemporalType.DATE)
	@Column(name="or_date")
	private Date orDate;

	@Column(name="or_ic_id")
	private int orIcId;

	@Column(name="or_id")
	private int orId;

	@Column(name="or_mt_item_num")
	private int orMtItemNum;


	public OrderSummary() {
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

	public int getIcId() {
		return this.icId;
	}

	public void setIcId(int icId) {
		this.icId = icId;
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

	public boolean getMtCustomizeable() {
		return this.mtCustomizeable;
	}

	public void setMtCustomizeable(boolean mtCustomizeable) {
		this.mtCustomizeable = mtCustomizeable;
	}

	public long getMtIcId() {
		return this.mtIcId;
	}

	public void setMtIcId(long mtIcId) {
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

	public long getMtOrSeq() {
		return this.mtOrSeq;
	}

	public void setMtOrSeq(long mtOrSeq) {
		this.mtOrSeq = mtOrSeq;
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

	public int getOrCbId() {
		return this.orCbId;
	}

	public void setOrCbId(int orCbId) {
		this.orCbId = orCbId;
	}

	public Date getOrDate() {
		return this.orDate;
	}

	public void setOrDate(Date orDate) {
		this.orDate = orDate;
	}

	public int getOrIcId() {
		return this.orIcId;
	}

	public void setOrIcId(int orIcId) {
		this.orIcId = orIcId;
	}

	public int getOrId() {
		return this.orId;
	}

	public void setOrId(int orId) {
		this.orId = orId;
	}

	public int getOrMtItemNum() {
		return this.orMtItemNum;
	}

	public void setOrMtItemNum(int orMtItemNum) {
		this.orMtItemNum = orMtItemNum;
	}

	public long getOrSeq() {
		return this.orSeq;
	}

	public void setOrSeq(long orSeq) {
		this.orSeq = orSeq;
	}

}