package com.vaadin.root.dto;

import java.time.LocalDate;

public class OrderFormDetails {

	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zipCode;
	private String emailAddress;
	private String phoneNumber;
			

	//cardInfo
	private String cardNumber;
	private LocalDate expirationDate;
	private String cvvCode;


	public OrderFormDetails(){
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



	public LocalDate getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(LocalDate expirationLocalDate) {
		this.expirationDate = expirationDate;
	}


	public String getCvvCode() {
		return cvvCode;
	}


	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "OrderFormDetails [firstName=" + this.getFirstName() + ", lastName=" + lastName + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", zipCode=" + zipCode + ", emailAddress=" + emailAddress
				+ ", cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + ", cvvCode=" + cvvCode + "]";
	}

	
}
