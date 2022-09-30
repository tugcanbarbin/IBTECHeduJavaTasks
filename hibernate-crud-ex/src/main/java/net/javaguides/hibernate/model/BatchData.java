package net.javaguides.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batch_data")
public class BatchData implements IModel {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "status")
	private int status;
	@Column(name = "accountnr")
	private String accountNr;
	@Column(name = "transactiontype")
	private String transactionType;
	@Column(name = "amount")
	private int amount;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAccountNr() {
		return accountNr;
	}
	public void setAccountNr(String accountNr) {
		this.accountNr = accountNr;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	@Override
	public String toString() {
		return "BatchData [id=" + id + ", status=" + status + ", accountNr=" + accountNr + ", transactionType="
				+ transactionType + ", amount=" + amount + "]";
	}

}
