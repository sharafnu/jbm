package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.CustomerContract;

public interface CustomerContractService {

	public Long createCustomerContract(CustomerContract customerContract);

	public List<CustomerContract> geCustomerContractListByCustomerId(
			Long customerId);
}
