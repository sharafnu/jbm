package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.entity.jdbc.extractor.EmployeeExtractor;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeExtractor employeeExtractor = new EmployeeExtractor();
		return employeeExtractor.extractData(rs);
	}
}
