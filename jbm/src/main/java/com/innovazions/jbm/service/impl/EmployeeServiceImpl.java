package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.AreaDAO;
import com.innovazions.jbm.dao.EmployeeDAO;
import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.service.AreaService;
import com.innovazions.jbm.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public void createEmployee(Employee employee) {
		employeeDAO.createEmployee(employee);
	}

	@Override
	public List<Employee> getEmployeeList(Employee employee) {
		return employeeDAO.getEmployeeList(employee);
	}


	
}
