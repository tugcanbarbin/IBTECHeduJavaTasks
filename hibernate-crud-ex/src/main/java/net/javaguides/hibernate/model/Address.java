package net.javaguides.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements IModel {
	@Id
	@Column(name = "cust_id")
	private int cust_id;
	@Column(name = "country")
	private String country;
	@Column(name = "city")
	private String city;
	@Column(name = "street")
	private String street;
	
	public Address() {};
	
	public Address(int cust_id, String country, String city,String street) {
		super();
		this.cust_id = cust_id;
		this.country = country;
		this.city = city;
		this.street= street;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [cust_id=" + cust_id + ", country=" + country + ", city=" + city + ", street=" + street + "]";
	}
	public String toXML() {
		return "<cust_id>" + this.cust_id + "</cust_id>" + "<country>" + this.country + "</country>" + "<city>" + this.city + "</city>"+ "<street>" + this.street + "</street>" ;
	}
	

}
