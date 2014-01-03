package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.Customer;

public interface CustomerService {

	public Long createCustomer(Customer customer);

	public List<Customer> getCustomerList(Customer customer);
	
	public Customer getCustomerDetailsByCustomerId(Long customerId);
	
}
