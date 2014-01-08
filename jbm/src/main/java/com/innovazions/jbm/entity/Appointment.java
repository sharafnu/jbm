package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.view.AppointmentView;

/**
 * The persistent class for the appointment database table.
 * 
 */
public class Appointment extends CoreEntity<Appointment, AppointmentView>
		implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date appointmentDate;

	private String appointmentNo;

	private String appointmentStatus;

	private String cancellationReason;

	private Integer hoursSpent;

	private double payableAmount;

	private String paymentStatus;

	private String remarks;

	private Date startDate;

	private Date endDate;

	private Area area;

	private Customer customer;

	private Employee employee;

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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public AppointmentView convertEntityToView() {
		AppointmentView appointmentView = new AppointmentView();
		appointmentView.setAppointmentDate(this.getAppointmentDate());
		appointmentView.setAppointmentNo(this.getAppointmentNo());
		appointmentView.setAppointmentStatus(this.getAppointmentStatus());
		if (this.getArea() != null) {
			appointmentView.setAreaId(this.getArea().getId());
			appointmentView.setAreaName(this.getArea().getName());
		}
		appointmentView.setCancellationReason(this.getCancellationReason());
		if (this.getCustomer() != null) {
			appointmentView.setCustomerId(this.getCustomer().getId());
			appointmentView.setCustomerName(this.getCustomer().getFullName());
		}
		if (this.getEmployee() != null) {
			appointmentView.setEmployeeId(this.getEmployee().getId());
			appointmentView.setEmployeeName(this.getEmployee().getFirstName());
		}
		appointmentView.setEndDate(this.getEndDate());
		appointmentView.setHoursSpent(this.getHoursSpent());
		appointmentView.setId(this.getId());
		appointmentView.setLastModifiedDate(this.getLastModifiedDate());
		appointmentView.setLastModifiedUser(this.getLastModifiedUser());
		appointmentView.setPayableAmount(this.getPayableAmount());
		appointmentView.setPaymentStatus(this.getPaymentStatus());
		appointmentView.setRemarks(this.getRemarks());
		appointmentView.setStartDate(this.getStartDate());
		if (this.getStartDate() != null) {
			appointmentView.setStartTime(CommonUtils.getTimeStrFromDate(this
					.getStartDate()));
		}
		if (this.getEndDate() != null) {
			appointmentView.setEndTime(CommonUtils.getTimeStrFromDate(this
					.getEndDate()));
		}
		return appointmentView;
	}

	@Override
	public List<AppointmentView> convertEntitiesToViews(
			List<Appointment> entityList) {
		List<AppointmentView> appointmentViewList = new ArrayList<AppointmentView>();
		for (Appointment appointment : entityList) {
			appointmentViewList.add(appointment.convertEntityToView());
		}
		return appointmentViewList;
	}

}