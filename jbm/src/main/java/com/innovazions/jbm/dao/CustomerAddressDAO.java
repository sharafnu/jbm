package com.innovazions.jbm.dao;

import com.innovazions.jbm.entity.CustomerAddress;

public interface CustomerAddressDAO {

	public long createCustomerAddress(CustomerAddress customerAddress);

	public long updateCustomerAddress(CustomerAddress customerAddress);

	public long deleteCustomerAddress(CustomerAddress customerAddress);

	public CustomerAddress getCustomerAddresssByCustomerId(Long customerId);

}
