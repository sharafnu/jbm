package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.CustomerAddress;

public interface CustomerAddressService {

	public Long createCustomerAddress(CustomerAddress customerAddress);

	public List<CustomerAddress> getCustomerAddressListByCustomerId(Long customerId);
	
}
