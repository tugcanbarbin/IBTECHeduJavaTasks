package net.javaguides.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer  implements IModel{
	@Id
	/* @GeneratedValue(strategy = GenerationType.AUTO) */
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "bdate")
	private String bdate;
	@Column(name = "bcountry")
	private String bcountry;
	
	
	public Customer() {};
	public Customer(int id, String name, String surname, String bdate, String bcountry) 
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.bdate = bdate;
		this.bcountry = bcountry;
	};
	
	
	public String getName() {return name;}
	public String getSurame() {return surname;}
	public String getBdate() {return bdate;}
	public String getBcountry() {return bcountry;}
	public int getId() {return id;}
	
	public void setName(String name) {this.name = name;}
	public void setSurname(String surname) {this.surname = surname;}
	public void setBdate(String bdate) {this.bdate = bdate;}
	public void setBcountry(String bcountry) {this.bcountry = bcountry;}
	public void setId(int id) {this.id = id;}
	
	@Override
	public String toString()
	{
		return String.format("[Id: %d Name: %s, Surname %s, bdate: %s, bcountry: %s ]",this.id, this.name, this.surname, this.bdate, this.bcountry);
	}
	public String toXML() {
		return "<id>" + this.id + "</id>" + "<name>" + this.name + "</name>" + "<surname>" + this.surname + "</surname>"+ "<bdate>" + this.bdate + "</bdate>"+ "<bcountry>" + this.bcountry + "</bcountry>"  ;
	}
}