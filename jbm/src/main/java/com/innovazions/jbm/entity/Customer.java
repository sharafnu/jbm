package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the customer database table.
 * 
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String customerCode;

	private String email;

	private String firstName;

	private Timestamp lastModifiedDate;

	private String lastModifiedUser;

	private String lastName;

	private String mobile1;

	private String mobile2;

	private String mobile3;

	private Integer preferenceCall;

	private Integer preferenceEmail;

	private Integer preferenceSms;


	public Customer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile1() {
		return this.mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return this.mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getMobile3() {
		return this.mobile3;
	}

	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}

	public Integer getPreferenceCall() {
		return this.preferenceCall;
	}

	public void setPreferenceCall(Integer preferenceCall) {
		this.preferenceCall = preferenceCall;
	}

	public Integer getPreferenceEmail() {
		return this.preferenceEmail;
	}

	public void setPreferenceEmail(Integer preferenceEmail) {
		this.preferenceEmail = preferenceEmail;
	}

	public Integer getPreferenceSms() {
		return this.preferenceSms;
	}

	public void setPreferenceSms(Integer preferenceSms) {
		this.preferenceSms = preferenceSms;
	}
}