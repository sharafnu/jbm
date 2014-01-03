package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.jdbc.extractor.CustomerExtractor;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerExtractor customerExtractor = new CustomerExtractor();
		return customerExtractor.extractData(rs);
	}
}
