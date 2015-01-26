package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.CustomerAddressDAO;
import com.innovazions.jbm.dao.CustomerDAO;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.service.CustomerService;

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
		if (customerAddressesList != null) {
			for (CustomerAddress customerAddress : customerAddressesList) {
				/*
				 * if (customerAddress.getArea() != null &&
				 * !CommonUtils.isEmpty(customerAddress .getBuildingName()) &&
				 * !CommonUtils.isEmpty(customerAddress.getFlatNo())) {
				 */
				customer.setId(customerId);
				customerAddress.setCustomer(customer);
				if (customerAddress.getArea() != null
						&& customerAddress.getArea().getId() <= 0) {
					customerAddress.getArea().setId(null);
					customerAddress.setArea(null);
				}
				if (customerAddress.getArea() != null
						|| (customerAddress.getLatitude() != null && customerAddress
								.getLongitude() != null)) {
					customerAddressDAO.createCustomerAddress(customerAddress);
				}
				// }
			}
		}
		return customerId;
	}

	@Override
	public boolean checkDuplicateMobileNo(String mobileNo) {
		return customerDAO.checkDuplicateMobileNo(mobileNo);
	}

	@Override
	public Customer findCustomerByPrimaryMobileNo(String mobileNo) {
		return customerDAO.findCustomerByPrimaryMobileNo(mobileNo);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);
	}
	
	@Override
	public void deleteCustomer(Customer customer) {
		customerDAO.deleteCustomer(customer);
	}

}
