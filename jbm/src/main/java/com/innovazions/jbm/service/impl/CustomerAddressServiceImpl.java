package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.CustomerAddressDAO;
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

	@Autowired
	private CustomerAddressDAO customerAddressDAO;

	@Override
	public Long createCustomerAddress(CustomerAddress customerAddress) {
		return customerAddressDAO.createCustomerAddress(customerAddress);
	}

	@Override
	public List<CustomerAddress> getCustomerAddressListByCustomerId(
			Long customerId) {
		return customerAddressDAO.getCustomerAddresssByCustomerId(customerId);
	}

}
