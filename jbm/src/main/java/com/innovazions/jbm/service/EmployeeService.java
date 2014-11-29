package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.Employee;

public interface EmployeeService {

	public Long createEmployee(Employee employee);

	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);
	
	public List<Employee> getEmployeeList(Employee employee);
	
	public Employee findEmployeeById(Long employeeId);
	
}
