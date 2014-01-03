package com.innovazions.jbm.view;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerContract;

public class CustomerContractView extends
		GenericView<CustomerContractView, CustomerContract> {

	private Long id;

	private double amount;

	private String contractNo;

	private String contractStatus;

	private String contractType;

	private Long customerId;

	private String customerName;

	private Date contractDate;

	private Date expiryDate;

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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public CustomerContract convertViewToEntity() {
		CustomerContract customerContract = new CustomerContract();
		customerContract.setAmount(this.getAmount());
		customerContract.setContractDate(this.getContractDate());
		customerContract.setContractNo(this.getContractNo());
		customerContract.setContractStatus(this.getContractStatus());
		customerContract.setContractType(this.getContractType());
		Customer customer = new Customer();
		customer.setId(this.getCustomerId());
		customerContract.setCustomer(customer);
		customerContract.setExpiryDate(this.getExpiryDate());
		customerContract.setId(this.getId());
		customerContract.setLastModifiedDate(this.getLastModifiedDate());
		customerContract.setLastModifiedUser(this.getLastModifiedUser());
		return customerContract;
	}

	@Override
	public List<CustomerContract> convertViewsToEntities(
			List<CustomerContractView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}
}
