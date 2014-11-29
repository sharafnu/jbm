package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.text.ParseException;
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

	private Customer customer;

	private Employee employee;

	private CustomerAddress customerAddress;

	// TODO : Check design later
	private AppointmentPayment appointmentPayment;

	private Invoice invoice;

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
		appointmentView.setCancellationReason(this.getCancellationReason());
		if (this.getCustomer() != null) {
			appointmentView.setCustomerId(this.getCustomer().getId());
			appointmentView.setCustomerName(this.getCustomer().getFullName());
		}
		if (this.getEmployee() != null) {
			appointmentView.setEmployeeId(this.getEmployee().getId());
			appointmentView.setEmployeeName(this.getEmployee().getFirstName());
		}

		if (this.getCustomer() != null
				&& !CommonUtils.isEmpty(this.getCustomer().getMobile1())) {
			appointmentView
					.setCustomerMobileNo(this.getCustomer().getMobile1());
		}
		if (this.getCustomerAddress() != null) {
			appointmentView.setCustomerAddressId(this.getCustomerAddress()
					.getId());
			appointmentView.setAddressRemarks(this.getCustomerAddress()
					.getRemarks());
			if (this.getCustomerAddress().getArea() != null) {
				appointmentView.setAreaId(this.getCustomerAddress().getArea()
						.getId());
				appointmentView.setAreaName(this.getCustomerAddress().getArea()
						.getName());
				if (this.getCustomerAddress().getArea().getCity() != null) {
					appointmentView.setCityId(this.getCustomerAddress()
							.getArea().getCity().getId());
					appointmentView.setCityName(this.getCustomerAddress()
							.getArea().getCity().getName());
				}
			}
			appointmentView.setBuildingName(this.getCustomerAddress()
					.getBuildingName());
			appointmentView.setFlatNo(this.getCustomerAddress().getFlatNo());
			appointmentView.setAddressType(this.getCustomerAddress()
					.getAddressType());

		}
		appointmentView.setEndDate(this.getEndDate());
		appointmentView.setStartDate(this.getStartDate());
		appointmentView.setHoursSpent(this.getHoursSpent());
		appointmentView.setId(this.getId());
		appointmentView.setLastModifiedDate(this.getLastModifiedDate());
		appointmentView.setLastModifiedUser(this.getLastModifiedUser());
		appointmentView.setPayableAmount(this.getPayableAmount());
		appointmentView.setPaymentStatus(this.getPaymentStatus());
		appointmentView.setRemarks(this.getRemarks());
		/*
		 * try {
		 * appointmentView.setStartDate(CommonUtils.getJavaScriptDateTimeObj
		 * (this.getStartDate())); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		if (this.getStartDate() != null) {
			appointmentView.setStartTime(CommonUtils.getTimeStrFromDate(this
					.getStartDate()));
		}
		if (this.getEndDate() != null) {
			appointmentView.setEndTime(CommonUtils.getTimeStrFromDate(this
					.getEndDate()));
		}
		if (this.getCustomerAddress() != null) {
			appointmentView.setCustomerAddressId(this.getCustomerAddress()
					.getId());
		}

		if (this.getAppointmentPayment() != null) {
			appointmentView.setPaidAmount(this.getAppointmentPayment()
					.getAmountPaid());
			appointmentView.setPaymentMode(this.getAppointmentPayment()
					.getPaymentMode());
		}

		if (this.getInvoice() != null) {
			appointmentView.setInvoiceDate(this.getInvoice().getInoviceDate());
			appointmentView.setInvoiceNo(this.getInvoice().getInvoiceNo());
			appointmentView.setInvoiceAmount(this.getInvoice().getAmount());
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

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	public AppointmentPayment getAppointmentPayment() {
		return appointmentPayment;
	}

	public void setAppointmentPayment(AppointmentPayment appointmentPayment) {
		this.appointmentPayment = appointmentPayment;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}