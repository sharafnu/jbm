package com.innovazions.jbm.view;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Employee;

public class EmployeeView extends GenericView<EmployeeView, Employee>{
	
	private Long id;

	private String employeeCode;

	private String firstName;

	private Date joinDate;

	private Date lastModifiedDate;

	private String lastModifiedUser;

	private String nationality;

	private String remarks;

	private double salary;

	@Override
	public Employee convertViewToEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> convertViewsToEntities(List<EmployeeView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
