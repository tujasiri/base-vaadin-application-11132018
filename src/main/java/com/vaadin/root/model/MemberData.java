package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the member_data database table.
 * 
 */
@Entity
@Table(name="member_data")
@NamedQuery(name="MemberData.findAll", query="SELECT m FROM MemberData m")
public class MemberData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="md_email")
	private String mdEmail;

	@Column(name="md_facebook")
	private String mdFacebook;

	@Column(name="md_fname")
	private String mdFname;
	
	@Id
	@Column(name="md_id")
	private BigInteger mdId;

	@Column(name="md_lname")
	private String mdLname;

	@Column(name="md_myspace")
	private String mdMyspace;

	@Column(name="md_notes")
	private String mdNotes;

	@Column(name="md_phone_number")
	private String mdPhoneNumber;

	@Column(name="md_twitter")
	private String mdTwitter;

	public MemberData() {
	}

	public String getMdEmail() {
		return this.mdEmail;
	}

	public void setMdEmail(String mdEmail) {
		this.mdEmail = mdEmail;
	}

	public String getMdFacebook() {
		return this.mdFacebook;
	}

	public void setMdFacebook(String mdFacebook) {
		this.mdFacebook = mdFacebook;
	}

	public String getMdFname() {
		return this.mdFname;
	}

	public void setMdFname(String mdFname) {
		this.mdFname = mdFname;
	}

	public BigInteger getMdId() {
		return this.mdId;
	}

	public void setMdId(BigInteger mdId) {
		this.mdId = mdId;
	}

	public String getMdLname() {
		return this.mdLname;
	}

	public void setMdLname(String mdLname) {
		this.mdLname = mdLname;
	}

	public String getMdMyspace() {
		return this.mdMyspace;
	}

	public void setMdMyspace(String mdMyspace) {
		this.mdMyspace = mdMyspace;
	}

	public String getMdNotes() {
		return this.mdNotes;
	}

	public void setMdNotes(String mdNotes) {
		this.mdNotes = mdNotes;
	}

	public String getMdPhoneNumber() {
		return this.mdPhoneNumber;
	}

	public void setMdPhoneNumber(String mdPhoneNumber) {
		this.mdPhoneNumber = mdPhoneNumber;
	}

	public String getMdTwitter() {
		return this.mdTwitter;
	}

	public void setMdTwitter(String mdTwitter) {
		this.mdTwitter = mdTwitter;
	}

}