package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.innovazions.jbm.view.CustomerView;

/**
 * The persistent class for the customer database table.
 * 
 */
public class Customer extends CoreEntity<Customer, CustomerView> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String customerCode;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile1;

	private String mobile2;

	private String mobile3;

	private Integer preferenceCall;

	private Integer preferenceEmail;

	private Integer preferenceSms;

	private List<CustomerAddress> customerAddressList;
	
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

	public String getFullName() {
		return ((this.getFirstName() != null ? this.getFirstName() + " " : "") + (this
				.getLastName() != null ? this.getLastName() : ""));
	}
	
	@Override
	public CustomerView convertEntityToView() {
		CustomerView customerView = new CustomerView();
		customerView.setCustomerCode(this.getCustomerCode());
		customerView.setEmail(this.getEmail());
		customerView.setFirstName(this.getFirstName());
		customerView.setId(this.getId());
		customerView.setLastModifiedDate(this.getLastModifiedDate());
		customerView.setLastModifiedUser(this.getLastModifiedUser());
		customerView.setLastName(this.getLastName());
		customerView.setMobile1(this.getMobile1());
		customerView.setMobile2(this.getMobile2());
		customerView.setMobile3(this.getMobile3());
		customerView.setPreferenceCall(this.getPreferenceCall());
		customerView.setPreferenceEmail(this.getPreferenceEmail());
		customerView.setPreferenceSms(this.getPreferenceSms());
		return customerView;
	}

	@Override
	public List<CustomerView> convertEntitiesToViews(List<Customer> entityList) {
		List<CustomerView> customerViewList = new ArrayList<CustomerView>();
		for (Customer customer : entityList) {
			customerViewList.add(customer.convertEntityToView());
		}
		return customerViewList;
	}

	public List<CustomerAddress> getCustomerAddressList() {
		return customerAddressList;
	}

	public void setCustomerAddressList(List<CustomerAddress> customerAddressList) {
		this.customerAddressList = customerAddressList;
	}
}