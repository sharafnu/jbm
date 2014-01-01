package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the customer_contract database table.
 * 
 */
public class CustomerContract implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private double amount;

	private Timestamp contractDate;

	private String contractNo;

	private String contractStatus;

	private String contractType;

	private Timestamp expiryDate;

	private Timestamp lastModifiedDate;

	private String lastModifiedUser;

	private Customer customer;

	public CustomerContract() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getContractDate() {
		return this.contractDate;
	}

	public void setContractDate(Timestamp contractDate) {
		this.contractDate = contractDate;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractStatus() {
		return this.contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Timestamp getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Timestamp getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedUser() {
		return this.lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}