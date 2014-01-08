package com.innovazions.jbm.view;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Customer;

public class CustomerView extends GenericView<CustomerView, Customer> {

	private Long id;

	private String customerCode;

	private String email;

	private String firstName;

	private Date lastModifiedDate;

	private String lastModifiedUser;

	private String lastName;

	private String mobile1;

	private String mobile2;

	private String mobile3;

	private Integer preferenceCall;

	private Integer preferenceEmail;

	private Integer preferenceSms;

	private String fullName;
	
	private String searchText;

	public CustomerView() {
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

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
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

	public String getFullName() {
		return ((this.getFirstName() != null ? this.getFirstName() + " " : "") + (this
				.getLastName() != null ? this.getLastName() : ""));
	}
	
	public String getComboBoxText() {
		return "<div class='dottedBox'> "+this.getFullName() + "<br/>" + this.getCustomerCode() + "<br/>"
				+ this.getMobile1()+"</div>";
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public Customer convertViewToEntity() {
		Customer customer = new Customer();
		customer.setCustomerCode(this.getCustomerCode());
		customer.setEmail(this.getEmail());
		customer.setFirstName(this.getFirstName());
		customer.setId(this.getId());
		customer.setLastModifiedDate(this.getLastModifiedDate());
		customer.setLastModifiedUser(this.getLastModifiedUser());
		customer.setLastName(this.getLastName());
		customer.setMobile1(this.getMobile1());
		customer.setMobile2(this.getMobile2());
		customer.setMobile3(this.getMobile3());
		customer.setPreferenceCall(this.getPreferenceCall());
		customer.setPreferenceEmail(this.getPreferenceEmail());
		customer.setPreferenceSms(this.getPreferenceSms());
		return customer;
	}

	@Override
	public List<Customer> convertViewsToEntities(List<CustomerView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}

}
