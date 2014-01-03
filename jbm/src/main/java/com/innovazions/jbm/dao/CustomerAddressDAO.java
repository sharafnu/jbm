package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.CustomerAddress;

public interface CustomerAddressDAO {

	public long createCustomerAddress(CustomerAddress customerAddress);

	public long updateCustomerAddress(CustomerAddress customerAddress);

	public long deleteCustomerAddress(CustomerAddress customerAddress);

	public List<CustomerAddress> getCustomerAddresssByCustomerId(Long customerId);

}
