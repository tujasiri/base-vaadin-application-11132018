package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the calendar database table.
 * 
 */
@Entity
@NamedQuery(name="Calendar.findAll", query="SELECT c FROM Calendar c")
public class Calendar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cal_address")
	private String calAddress;

	@Column(name="cal_comments")
	private String calComments;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cal_date")
	private Date calDate;

	@Column(name="cal_event")
	private String calEvent;

	@Lob
	@Column(name="cal_flyer")
	private byte[] calFlyer;
	
	@Id
	@Column(name="cal_id")
	private int calId;

	@Lob
	@Column(name="cal_rsvp")
	private String calRsvp;

	@Column(name="cal_time")
	private Time calTime;

	@Column(name="cal_venue")
	private String calVenue;

	public Calendar() {
	}

	public String getCalAddress() {
		return this.calAddress;
	}

	public void setCalAddress(String calAddress) {
		this.calAddress = calAddress;
	}

	public String getCalComments() {
		return this.calComments;
	}

	public void setCalComments(String calComments) {
		this.calComments = calComments;
	}

	public Date getCalDate() {
		return this.calDate;
	}

	public void setCalDate(Date calDate) {
		this.calDate = calDate;
	}

	public String getCalEvent() {
		return this.calEvent;
	}

	public void setCalEvent(String calEvent) {
		this.calEvent = calEvent;
	}

	public byte[] getCalFlyer() {
		return this.calFlyer;
	}

	public void setCalFlyer(byte[] calFlyer) {
		this.calFlyer = calFlyer;
	}

	public int getCalId() {
		return this.calId;
	}

	public void setCalId(int calId) {
		this.calId = calId;
	}

	public String getCalRsvp() {
		return this.calRsvp;
	}

	public void setCalRsvp(String calRsvp) {
		this.calRsvp = calRsvp;
	}

	public Time getCalTime() {
		return this.calTime;
	}

	public void setCalTime(Time calTime) {
		this.calTime = calTime;
	}

	public String getCalVenue() {
		return this.calVenue;
	}

	public void setCalVenue(String calVenue) {
		this.calVenue = calVenue;
	}

}