package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.CustomerContract;

public interface CustomerContractDAO {

	public long createCustomerContract(CustomerContract customerContract);

	public long updateCustomerContract(CustomerContract customerContract);

	public long deleteCustomerContract(CustomerContract customerContract);

	public List<CustomerContract> getCustomerContractByCustomerId(
			Long customerId);

	public List<CustomerContract> geCustomerActiveContractListByCustomerId(
			Long customerId);

}
