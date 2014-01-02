package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.Employee;

public class EmployeeExtractor implements ResultSetExtractor<Employee> {

	@Override
	public Employee extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Employee employee = new Employee();
		employee.setId(rs.getLong("id"));
		employee.setEmployeeCode(rs.getString("employee_code"));
		employee.setFirstName(rs.getString("first_name"));
		employee.setNationality(rs.getString("nationality"));
		employee.setJoinDate(rs.getDate("join_date"));
		employee.setSalary(rs.getDouble("salary"));
		employee.setRemarks(rs.getString("remarks"));
		return employee;
	}

}
