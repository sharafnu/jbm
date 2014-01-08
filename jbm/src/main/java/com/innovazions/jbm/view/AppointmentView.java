package com.innovazions.jbm.view;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.Employee;

public class AppointmentView extends GenericView<AppointmentView, Appointment> {

	private Long id;

	private Date appointmentDate;

	private String appointmentNo;

	private String appointmentStatus;

	private String cancellationReason;

	private Integer hoursSpent;

	private double payableAmount;

	private String paymentStatus;

	private String remarks;

	private String startTime;

	private Date startDate;

	private Date endDate;

	private String endTime;

	private Long areaId;

	private String areaName;

	private Long customerId;

	private String customerName;

	private Long employeeId;

	private String employeeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public Integer getHoursSpent() {
		return hoursSpent;
	}

	public void setHoursSpent(Integer hoursSpent) {
		this.hoursSpent = hoursSpent;
	}

	public double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Override
	public Appointment convertViewToEntity() {
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(this.getAppointmentDate());
		appointment.setAppointmentNo(this.getAppointmentNo());
		appointment.setAppointmentStatus(this.getAppointmentStatus());
		Area area = new Area();
		area.setId(this.getAreaId());
		appointment.setArea(area);
		appointment.setCancellationReason(this.getCancellationReason());
		Customer customer = new Customer();
		customer.setId(this.getCustomerId());
		appointment.setCustomer(customer);
		Employee employee = new Employee();
		employee.setId(this.getEmployeeId());
		appointment.setEmployee(employee);
		appointment.setHoursSpent(this.getHoursSpent());
		appointment.setId(this.getId());
		appointment.setLastModifiedDate(this.getLastModifiedDate());
		appointment.setLastModifiedUser(this.getLastModifiedUser());
		appointment.setPayableAmount(this.getPayableAmount());
		appointment.setPaymentStatus(this.getPaymentStatus());
		appointment.setRemarks(this.getRemarks());

		if (!CommonUtils.isEmpty(this.getStartTime())) {
			try {
				appointment.setStartDate(CommonUtils.addTimeStringToDate(
						appointment.getAppointmentDate(), this.getStartTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				appointment.setStartDate(appointment.getAppointmentDate());
			}
		}

		if (!CommonUtils.isEmpty(this.getEndTime())) {
			try {
				appointment.setEndDate(CommonUtils.addTimeStringToDate(
						appointment.getAppointmentDate(), this.getEndTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				appointment.setEndDate(appointment.getAppointmentDate());
			}
		}

		return appointment;
	}

	@Override
	public List<Appointment> convertViewsToEntities(
			List<AppointmentView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
