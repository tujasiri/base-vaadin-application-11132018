package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the business_info database table.
 * 
 */
@Entity
@Table(name="business_info")
@NamedQueries(value = { 
@NamedQuery(name="BusinessInfo.findAll", query="SELECT b FROM BusinessInfo b"),
@NamedQuery(name="BusinessInfo.findById", 
			query="SELECT b "
					+ "FROM BusinessInfo b "
					+ "WHERE b.biId = :idx")
})
public class BusinessInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="bi_address")
	private String biAddress;

	@Column(name="bi_address2")
	private String biAddress2;

	@Column(name="bi_city")
	private String biCity;

	@Id
	@Column(name="bi_id")
	private Long biId;

	@Lob
	@Column(name="bi_logo")
	private byte[] biLogo;

	@Column(name="bi_name")
	private String biName;

	@Column(name="bi_about")
	private String biAbout;

	@Column(name="bi_sm_id")
	private Long biSmId;

	@Column(name="bi_state")
	private String biState;

	@Column(name="bi_zip")
	private String biZip;

	@Column(name="bi_website")
	private String biWebsite;

	@Lob
	@Column(name="bi_header")
	private byte[] biHeader;


	public BusinessInfo() {
	}

	public String getBiAddress() {
		return this.biAddress;
	}

	public void setBiAddress(String biAddress) {
		this.biAddress = biAddress;
	}

	public String getBiAddress2() {
		return this.biAddress2;
	}

	public void setBiAddress2(String biAddress2) {
		this.biAddress2 = biAddress2;
	}

	public String getBiCity() {
		return this.biCity;
	}

	public void setBiCity(String biCity) {
		this.biCity = biCity;
	}

	public Long getBiId() {
		return this.biId;
	}

	public void setBiId(Long biId) {
		this.biId = biId;
	}

	public byte[] getBiLogo() {
		return this.biLogo;
	}

	public void setBiLogo(byte[] biLogo) {
		this.biLogo = biLogo;
	}

	public String getBiName() {
		return this.biName;
	}

	public void setBiName(String biName) {
		this.biName = biName;
	}

	public Long getBiSmId() {
		return this.biSmId;
	}

	public void setBiSmId(Long biSmId) {
		this.biSmId = biSmId;
	}

	public String getBiState() {
		return this.biState;
	}

	public void setBiState(String biState) {
		this.biState = biState;
	}

	public String getBiWebsite() {
		return this.biWebsite;
	}

	public void setBiWebsite(String biWebsite) {
		this.biWebsite = biWebsite;
	}

	public String getBiAbout() {
		return biAbout;
	}

	public void setBiAbout(String biAbout) {
		this.biAbout = biAbout;
	}

	public String getBiZip() {
		return biZip;
	}

	public void setBiZip(String biZip) {
		this.biZip = biZip;
	}

	public byte[] getBiHeader() {
		return biHeader;
	}

	public void setBiHeader(byte[] biHeader) {
		this.biHeader = biHeader;
	}
	
	

}