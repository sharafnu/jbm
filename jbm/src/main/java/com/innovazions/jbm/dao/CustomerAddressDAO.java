package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.CustomerAddress;

public interface CustomerAddressDAO {

	public long createCustomerAddress(CustomerAddress customerAddress);

	public void updateCustomerAddress(CustomerAddress customerAddress);

	public List<CustomerAddress> getCustomerAddresssByCustomerId(Long customerId);

	void deleteCustomerAddress(Long customerAddressId);

}
