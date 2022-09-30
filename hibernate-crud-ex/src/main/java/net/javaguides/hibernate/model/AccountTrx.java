package net.javaguides.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounttrx")
public class AccountTrx implements IModel{
	@Id
	@Column(name = "accountnr")
	private String accountNr;
	@Column(name = "balance")
	private int balance;

	
	
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
	
	@Override
	public String toString() {
		return "AccountTrx [accountNr=" + accountNr + ", balance=" + balance + "]";
	}

}
