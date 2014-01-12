package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.entity.jdbc.extractor.CustomerAddressExtractor;

public class CustomerAddressRowMapper implements RowMapper<CustomerAddress> {

	@Override
	public CustomerAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerAddressExtractor customerAddressExtractor = new CustomerAddressExtractor();
		return customerAddressExtractor.extractData(rs);
	}
}
