package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.CustomerDAO;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public Long createCustomer(Customer customer) {
		return customerDAO.createCustomer(customer);
	}

	@Override
	public List<Customer> getCustomerList(Customer customer) {
		return customerDAO.getCustomerList(customer);
	}

	@Override
	public Customer getCustomerDetailsByCustomerId(Long customerId) {
		return customerDAO.getCustomerDetailsByCustomerId(customerId);
	}

	
}
