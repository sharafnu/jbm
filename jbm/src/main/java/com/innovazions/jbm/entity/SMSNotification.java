package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the area database table.
 * 
 */
public class SMSNotification extends
		CoreEntity<SMSNotification, SMSNotification> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Customer customer;

	private Date sentDate;

	private String toMobileNumber;
	
	private String messageContent;

	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getToMobileNumber() {
		return toMobileNumber;
	}

	public void setToMobileNumber(String toMobileNumber) {
		this.toMobileNumber = toMobileNumber;
	}

	@Override
	public SMSNotification convertEntityToView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public List<SMSNotification> convertEntitiesToViews(
			List<SMSNotification> entityList) {
		// TODO Auto-generated method stub
		return entityList;
	}
}