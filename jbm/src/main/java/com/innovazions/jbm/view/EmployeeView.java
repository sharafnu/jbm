package com.innovazions.jbm.view;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Employee;

public class EmployeeView extends GenericView<EmployeeView, Employee> {

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

	@Override
	public Employee convertViewToEntity() {
		Employee employee = new Employee();
		employee.setEmployeeCode(this.getEmployeeCode());
		employee.setFirstName(this.getFirstName());
		employee.setId(this.getId());
		employee.setJoinDate(this.getJoinDate());
		employee.setNationality(this.getNationality());
		employee.setRemarks(this.getRemarks());
		employee.setSalary(this.getSalary());
		employee.setContactMobileNo(this.getContactMobileNo());
		employee.setHomeCountryContactNo(this.getHomeCountryContactNo());
		employee.setAddress(this.getAddress());
		employee.setPassportNo(this.getPassportNo());
		employee.setVisaDetails(this.getVisaDetails());
		employee.setEmployeeStatus(this.getEmployeeStatus());
		return employee;
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
