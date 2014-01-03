package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innovazions.jbm.view.CustomerContractView;

/**
 * The persistent class for the customer_contract database table.
 * 
 */
public class CustomerContract extends
		CoreEntity<CustomerContract, CustomerContractView> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private double amount;

	private String contractNo;

	private String contractStatus;

	private String contractType;

	private Customer customer;

	private Date contractDate;

	private Date expiryDate;

	public CustomerContract() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public CustomerContractView convertEntityToView() {
		CustomerContractView customerContractView = new CustomerContractView();
		customerContractView.setAmount(this.getAmount());
		customerContractView.setContractDate(this.getContractDate());
		customerContractView.setContractNo(this.getContractNo());
		customerContractView.setContractStatus(this.getContractStatus());
		customerContractView.setContractType(this.getContractType());
		if (this.getCustomer() != null) {
			customerContractView.setCustomerId(this.getCustomer().getId());
			customerContractView.setCustomerName(this.getCustomer()
					.getFullName());
		}
		customerContractView.setExpiryDate(this.getExpiryDate());
		customerContractView.setId(this.getId());
		customerContractView.setLastModifiedDate(this.getLastModifiedDate());
		customerContractView.setLastModifiedUser(this.getLastModifiedUser());
		return customerContractView;
	}

	@Override
	public List<CustomerContractView> convertEntitiesToViews(
			List<CustomerContract> entityList) {
		List<CustomerContractView> contractViewList = new ArrayList<CustomerContractView>();
		for (CustomerContract customerContract : entityList) {
			contractViewList.add(customerContract.convertEntityToView());
		}
		return contractViewList;
	}

}