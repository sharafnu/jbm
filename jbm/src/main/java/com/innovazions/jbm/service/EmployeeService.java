package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.Employee;

public interface EmployeeService {

	public void createEmployee(Employee employee);

	public List<Employee> getEmployeeList(Employee employee);
	
}
