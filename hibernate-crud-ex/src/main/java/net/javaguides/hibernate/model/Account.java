package net.javaguides.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account implements IModel {
	
	@Id
	@Column(name = "cust_id")
	private int cust_id;
	@Column(name = "accountnr")
	private String accountNr;
	@Column(name = "balance")
	private int balance;
	
	public Account(int cust_id, String accountNr, int balance) {
		super();
		this.cust_id = cust_id;
		this.accountNr = accountNr;
		this.balance = balance;
	}

	public Account() {};
	
	@Override
	public String toString() {
		return "Account [cust_id=" + cust_id + ", accountNr=" + accountNr + ", balance=" + balance + "]";
	}
	public String toXML() {
		return "<cust_id>" + this.cust_id + "</cust_id>" + "<accountNr>" + this.accountNr + "</accountNr>" + "<balance>" + this.balance + "</balance>" ;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getAccountNr() {
		return accountNr;
	}
	public void setAccountNr(String accountNr) {
		this.accountNr = accountNr;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	
}
