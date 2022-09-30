package net.javaguides.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone implements IModel {
	@Id
	@Column(name = "cust_id")
	private int cust_id;
	@Column(name = "countrycode")
	private String countryCode;
	@Column(name = "phone_number")
	private String phoneNumber;
	
	public Phone() {}

	public Phone(int cust_id, String countryCode, String phoneNumber) {
		super();
		this.cust_id = cust_id;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Phone [cust_id=" + cust_id + ", countryCode=" + countryCode + ", phoneNumber=" + phoneNumber + "]";
	};
	public String toXML() {
		return "<cust_id>" + this.cust_id + "</cust_id>" + "<countryCode>" + this.countryCode + "</countryCode>" + "<phoneNumber>" + this.phoneNumber + "</phoneNumber>" ;
	}
	
	

}
