package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the invoice database table.
 * 
 */
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private double amount;

	private Date inoviceDate;

	private String invoiceNo;

	private String status;

	public Invoice() {
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

	public Date getInoviceDate() {
		return this.inoviceDate;
	}

	public void setInoviceDate(Date inoviceDate) {
		this.inoviceDate = inoviceDate;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}