package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.CustomerContract;
import com.innovazions.jbm.entity.jdbc.extractor.CustomerContractExtractor;

public class CustomerContractRowMapper implements RowMapper<CustomerContract> {

	@Override
	public CustomerContract mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		CustomerContractExtractor customerContractExtractor = new CustomerContractExtractor();
		return customerContractExtractor.extractData(rs);
	}
}
