package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerContract;

public class CustomerContractExtractor implements
		ResultSetExtractor<CustomerContract> {

	@Override
	public CustomerContract extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		CustomerContract customerContract = new CustomerContract();
		customerContract.setAmount(rs.getDouble("amount"));
		customerContract.setContractDate(rs.getDate("contract_date"));
		customerContract.setContractNo(rs.getString("contract_no"));
		customerContract.setContractStatus(rs.getString("contract_status"));
		customerContract.setContractType(rs.getString("contract_type"));
		Customer customer = new Customer();
		customer.setId(rs.getLong("customer_id"));
		customerContract.setCustomer(customer);
		customerContract.setExpiryDate(rs.getDate("expiry_date"));
		customerContract.setId(rs.getLong("customer_contract_id"));
		customerContract.setLastModifiedDate(rs.getDate("last_modified_date"));
		customerContract
				.setLastModifiedUser(rs.getString("last_modified_user"));
		customerContract.setVisitCount(rs.getInt("visit_count"));
		//customerContract.setUtilizedVisitCount(rs.getInt("utilizedVisitCount"));
		return customerContract;
	}

}
