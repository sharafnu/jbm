package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.CustomerContractDAO;
import com.innovazions.jbm.entity.CustomerContract;
import com.innovazions.jbm.service.CustomerContractService;

@Service
public class CustomerContractServiceImpl implements CustomerContractService {

	@Autowired
	private CustomerContractDAO customerContractDAO;

	@Override
	public Long createCustomerContract(CustomerContract customerContract) {
		return customerContractDAO.createCustomerContract(customerContract);
	}

	@Override
	public List<CustomerContract> geCustomerContractListByCustomerId(
			Long customerId) {
		return customerContractDAO.getCustomerContractByCustomerId(customerId);
	}

	@Override
	public List<CustomerContract> geCustomerActiveContractListByCustomerId(
			Long customerId) {
		return customerContractDAO.geCustomerActiveContractListByCustomerId(customerId);
	}

}
