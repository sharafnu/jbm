package com.innovazions.jbm.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.dao.CustomerContractDAO;
import com.innovazions.jbm.entity.CustomerContract;
import com.innovazions.jbm.entity.jdbc.mapper.CustomerContractRowMapper;

@Repository
public class CustomerContractDAOImpl implements CustomerContractDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;

	@Override
	public long createCustomerContract(CustomerContract customerContract) {
		System.out.println("Inserting CustomerContract..");
		final String sql = "INSERT INTO customer_contract(customer_id, contract_date, expiry_date, "
				+ "contract_no, contract_type, amount, last_modified_date, last_modified_user, contract_status, visit_count) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sequence = new PostgreSQLSequenceMaxValueIncrementer(dataSource,
				"customer_contract_id_seq");
		jdbcTemplate.update(
				sql,
				new Object[] {
						customerContract.getCustomer().getId(),
						CommonUtils.getTimeStampFromDate(customerContract
								.getContractDate()),
						CommonUtils.getTimeStampFromDate(customerContract
								.getExpiryDate()),
						customerContract.getContractNo(),
						customerContract.getContractType(),
						customerContract.getAmount(),
						customerContract.getLastModifiedDate(),
						customerContract.getLastModifiedUser(),
						customerContract.getContractStatus(),
						customerContract.getVisitCount() });
		return sequence.nextLongValue() - 1;
	}

	@Override
	public long updateCustomerContract(CustomerContract customerContract) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteCustomerContract(CustomerContract customerContract) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomerContract> getCustomerContractByCustomerId(
			Long customerId) {

		String sql = "SELECT cc.id as customer_contract_id, cc.customer_id as customer_id, cc.contract_date as contract_date, "
				+ "cc.expiry_date as expiry_date, cc.contract_no as contract_no, cc.contract_type as contract_type, "
				+ "cc.amount as amount, cc.last_modified_date as last_modified_date, cc.last_modified_user as last_modified_user, "
				+ "cc.contract_status as contract_status, cc.visit_count as visit_count FROM customer_contract cc "
				+ "inner join customer c on c.id=cc.customer_id where c.id=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustomerContract> customerContractList = jdbcTemplate.query(sql,
				new Object[] { customerId }, new CustomerContractRowMapper());
		return customerContractList;
	}
}