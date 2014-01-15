package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innovazions.jbm.view.EmployeeView;

/**
 * The persistent class for the employee database table.
 * 
 */
public class Employee extends CoreEntity<Employee, EmployeeView> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String employeeCode;

	private String firstName;

	private Date joinDate;

	private Date lastModifiedDate;

	private String lastModifiedUser;

	private String nationality;

	private String remarks;

	private double salary;

	private String contactMobileNo;
	
	private String homeCountryContactNo;
	
	private String address;
	
	private String passportNo;
	
	private String visaDetails;
	
	private String employeeStatus;
	
	public Employee() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeCode() {
		return this.employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedUser() {
		return this.lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public EmployeeView convertEntityToView() {
		EmployeeView employeeView = new EmployeeView();
		employeeView.setId(this.getId());
		employeeView.setEmployeeCode(this.getEmployeeCode());
		employeeView.setFirstName(this.getFirstName());
		employeeView.setJoinDate(this.getJoinDate());
		employeeView.setNationality(this.getNationality());
		employeeView.setRemarks(this.getRemarks());
		employeeView.setSalary(this.getSalary());
		employeeView.setContactMobileNo(this.getContactMobileNo());
		employeeView.setHomeCountryContactNo(this.getHomeCountryContactNo());
		employeeView.setAddress(this.getAddress());
		employeeView.setPassportNo(this.getPassportNo());
		employeeView.setVisaDetails(this.getVisaDetails());
		employeeView.setEmployeeStatus(this.getEmployeeStatus());
		return employeeView;
	}

	@Override
	public List<EmployeeView> convertEntitiesToViews(List<Employee> entityList) {
		List<EmployeeView> employeeViewList = new ArrayList<EmployeeView>();
		for (Employee employee : entityList) {
			employeeViewList.add(employee.convertEntityToView());
		}
		return employeeViewList;
	}

	public String getContactMobileNo() {
		return contactMobileNo;
	}

	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}

	public String getHomeCountryContactNo() {
		return homeCountryContactNo;
	}

	public void setHomeCountryContactNo(String homeCountryContactNo) {
		this.homeCountryContactNo = homeCountryContactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getVisaDetails() {
		return visaDetails;
	}

	public void setVisaDetails(String visaDetails) {
		this.visaDetails = visaDetails;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

}