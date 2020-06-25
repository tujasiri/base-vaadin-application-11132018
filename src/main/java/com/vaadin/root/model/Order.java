package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="or_seq")
	private long orSeq;

	@Column(name="or_cb_id")
	private int orCbId;

	@Temporal(TemporalType.DATE)
	@Column(name="or_date")
	private Date orDate;

	@Column(name="or_ic_id")
	private long orIcId;

	@Column(name="or_id")
	private int orId;


	@Column(name="or_mt_item_num")
	private int orMtItemNum;

	public Order() {
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

	public long getOrIcId() {
		return this.orIcId;
	}

	public void setOrIcId(long orIcId) {
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
		return orSeq;
	}

	public void setOrSeq(long orSeq) {
		this.orSeq = orSeq;
	}

}