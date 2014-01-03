package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.Customer;

public class CustomerExtractor implements ResultSetExtractor<Customer> {

	@Override
	public Customer extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Customer customer = new Customer();
		customer.setId(rs.getLong("id"));
		customer.setCustomerCode(rs.getString("customer_code"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setMobile1(rs.getString("mobile_1"));
		customer.setMobile2(rs.getString("mobile_2"));
		customer.setMobile3(rs.getString("mobile_3"));
		customer.setEmail(rs.getString("email"));
		customer.setPreferenceCall(rs.getInt("preference_call"));
		customer.setPreferenceEmail(rs.getInt("preference_email"));
		customer.setPreferenceSms(rs.getInt("preference_sms"));
		customer.setLastModifiedDate(rs.getDate("last_modified_date"));
		customer.setLastModifiedUser(rs.getString("last_modified_user"));
		return customer;
	}

}
