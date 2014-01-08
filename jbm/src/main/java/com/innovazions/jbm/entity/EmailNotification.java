package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the area database table.
 * 
 */
public class EmailNotification extends
		CoreEntity<EmailNotification, EmailNotification> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Customer customer;

	private String fromEmailAddress;
	
	private String toEmailAddress;
	
	private Date sentDate;

	private String messageSubject;

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

	public String getMessageSubject() {
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
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

	@Override
	public EmailNotification convertEntityToView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public List<EmailNotification> convertEntitiesToViews(
			List<EmailNotification> entityList) {
		// TODO Auto-generated method stub
		return entityList;
	}

	public String getFromEmailAddress() {
		return fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

}