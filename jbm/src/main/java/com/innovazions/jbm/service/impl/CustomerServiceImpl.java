package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.CustomerAddressDAO;
import com.innovazions.jbm.dao.CustomerDAO;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.service.CustomerService;
import com.innovazions.jbm.view.CustomerAndAddressView;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private CustomerAddressDAO customerAddressDAO;

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

	@Override
	public Long createCustomerAndAddress(Customer customer) {

		Long customerId = customerDAO.createCustomer(customer);
		List<CustomerAddress> customerAddressesList = customer
				.getCustomerAddressList();
		if(customerAddressesList != null) {
			for (CustomerAddress customerAddress : customerAddressesList) {
				customer.setId(customerId);
				customerAddress.setCustomer(customer);
				customerAddressDAO.createCustomerAddress(customerAddress);
			}
		}
		return customerId;
	}

}
