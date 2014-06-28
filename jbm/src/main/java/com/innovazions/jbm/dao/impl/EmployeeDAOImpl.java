package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.dao.EmployeeDAO;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.entity.jdbc.mapper.CustomerRowMapper;
import com.innovazions.jbm.entity.jdbc.mapper.EmployeeRowMapper;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;

	@Override
	public long createEmployee(Employee employee) {
		System.out.println("Inserting Employee..");
		String sql = "INSERT INTO employee (employee_code,first_name, nationality, join_date, salary, remarks, "
				+ "contact_mobile_no, home_cntry_contact_no, address, passport_no, visa_details, employee_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		sequence = new PostgreSQLSequenceMaxValueIncrementer(dataSource,
				"employee_id_seq");
		jdbcTemplate
				.update(sql,
						new Object[] {
								employee.getEmployeeCode(),
								employee.getFirstName(),
								employee.getNationality(),
								CommonUtils.getTimeStampFromDate(employee
										.getJoinDate()), employee.getSalary(),
								employee.getRemarks(),
								employee.getContactMobileNo(),
								employee.getHomeCountryContactNo(),
								employee.getAddress(),
								employee.getPassportNo(),
								employee.getVisaDetails(),
								employee.getEmployeeStatus() });

		return sequence.nextLongValue() - 1;
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
				+ "e.join_date, e.salary, e.remarks, e.contact_mobile_no, e.home_cntry_contact_no,  "
				+ "e.address, e.passport_no, e.visa_details, e.employee_status from employee e";

		if (employee != null) {
			if (!CommonUtils.isEmpty(employee.getEmployeeStatus())) {
				sql = sql + " where e.employee_status='"
						+ employee.getEmployeeStatus() + "'";
			}
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		employeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
		return employeeList;
	}

	@Override
	public Employee findEmployeeById(Long employeeId) {

		String sql = "select e.id, e.employee_code, e.first_name,e.nationality, "
				+ "e.join_date, e.salary, e.remarks, e.contact_mobile_no, e.home_cntry_contact_no,  "
				+ "e.address, e.passport_no, e.visa_details, e.employee_status from employee e where e.id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Employee> employeeList = jdbcTemplate.query(sql,
				new Object[] { employeeId }, new EmployeeRowMapper());
		if (employeeList.isEmpty()) {
			return null;
		} else {
			return employeeList.get(0);
		}
	}
}
