package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.EmployeeDAO;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.entity.jdbc.mapper.EmployeeRowMapper;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public long createEmployee(Employee employee) {
		System.out.println("Inserting Employee..");
		String sql = "INSERT INTO employee (employee_code,first_name, nationality, join_date, salary, remarks) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { employee.getEmployeeCode(),
						employee.getFirstName(), employee.getNationality(),
						employee.getJoinDate(), employee.getSalary(),
						employee.getRemarks() });
		return 0;
	}

	@Override
	public long updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employee> getEmployeeList(Employee employee) {
		List<Employee> employeeList = new ArrayList<Employee>();

		String sql = "select e.id, e.employee_code, e.first_name,e.nationality, "
				+ "e.join_date, e.salary, e.remarks from employee e";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		employeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
		return employeeList;
	}

}
