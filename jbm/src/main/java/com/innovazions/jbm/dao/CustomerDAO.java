package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.Customer;

public interface CustomerDAO {

	public long createCustomer(Customer customer);

	public long updateCustomer(Customer customer);

	public long deleteCustomer(Customer customer);

	public List<Customer> getCustomerList(Customer customer);

	public Customer getCustomerDetailsByCustomerId(Long customerId);

	public boolean checkDuplicateMobileNo(String mobileNo);

}
