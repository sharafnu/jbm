package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.Employee;

public interface EmployeeDAO {

	public long createEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

	public List<Employee> getEmployeeList(Employee employee);

	Employee findEmployeeById(Long employeeId);

}
