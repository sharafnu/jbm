package com.innovazions.jbm.dao.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.CustomerAddressDAO;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.entity.jdbc.mapper.CustomerRowMapper;

@Repository
public class CustomerAddressDAOImpl implements CustomerAddressDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;
	
	@Override
	public long createCustomerAddress(CustomerAddress customerAddress) {
		System.out.println("Inserting CustomerAddress..");
		final String sql = "INSERT INTO customer (first_name, last_name, mobile_1, mobile_2, mobile_3, email, preference_call, "
				+ "preference_email, preference_sms, last_modified_date, last_modified_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sequence = new PostgreSQLSequenceMaxValueIncrementer(dataSource,
				"customer_id_seq");
		jdbcTemplate.update(
				sql,
				new Object[] { customer.getFirstName(), customer.getLastName(),
						customer.getMobile1(), customer.getMobile2(),
						customer.getMobile3(), customer.getEmail(),
						customer.getPreferenceCall(),
						customer.getPreferenceEmail(),
						customer.getPreferenceSms(), new Date(),
						customer.getLastModifiedUser() });

		// Get inserted employee id from the sequence

		/*
		 * KeyHolder holder = new GeneratedKeyHolder();
		 * 
		 * jdbcTemplate.update(new PreparedStatementCreator() {
		 * 
		 * @Override public PreparedStatement createPreparedStatement(
		 * Connection connection) throws SQLException { PreparedStatement ps =
		 * connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		 * ps.setString(1, customer.getFirstName()); ps.setString(2,
		 * customer.getLastName()); ps.setString(3, customer.getMobile1());
		 * ps.setString(4, customer.getMobile2()); ps.setString(4,
		 * customer.getMobile3()); ps.setString(4, customer.getEmail());
		 * ps.setInt(4, customer.getPreferenceCall()); ps.setInt(4,
		 * customer.getPreferenceEmail()); ps.setInt(4,
		 * customer.getPreferenceSms()); ps.setDate(4, new
		 * java.sql.Date(System.currentTimeMillis())); ps.setString(4,
		 * customer.getLastModifiedUser());
		 * 
		 * return ps; } }, holder);
		 * 
		 * Long newCustomerId = holder.getKey().longValue();
		 */

		return sequence.nextLongValue() - 1;
	}

	@Override
	public long updateCustomerAddress(CustomerAddress customerAddress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteCustomerAddress(CustomerAddress customerAddress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CustomerAddress getCustomerAddresssByCustomerId(Long customerId) {

		String sql = "select c.id, c.customer_code, c.first_name,c.last_name, c.mobile_1, c.mobile_2, c.mobile_3, "
				+ "c.email, c.preference_call, c.preference_email, c.preference_sms, c.last_modified_date, "
				+ "c.last_modified_user from customer c where c.id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Customer> customerList = jdbcTemplate.query(sql,
				new Object[] { customerId }, new CustomerRowMapper());
		if (customerList.isEmpty()) {
			return null;
		} else {
			return customerList.get(0);
		}
	}

}
