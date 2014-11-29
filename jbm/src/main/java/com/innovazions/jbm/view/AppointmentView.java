package com.innovazions.jbm.view;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.AppointmentPayment;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.entity.Invoice;

public class AppointmentView extends GenericView<AppointmentView, Appointment> {

	private Long id;

	private Date appointmentDate;

	private String appointmentNo;

	private String appointmentStatus;

	private String cancellationReason;

	private Integer hoursSpent;

	private double payableAmount;

	private String paymentStatus;

	private String paymentType;

	private String invoiceNo;

	private Date invoiceDate;

	private double invoiceAmount;
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

	private Long customerAddressId;

	private String addressType;

	private String buildingName;

	private String flatNo;

	private String cityName;

	private String customerMobileNo;

	private String addressRemarks;

	private Long cityId;

	private String startDateFilter;

	private String endDateFilter;
	
	private double paidAmount;
	
	private String paymentMode;

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
		if (this.getId() != null && this.getId() > 0) {
			appointment.setId(this.getId());
		}
		
		appointment.setAppointmentDate(this.getAppointmentDate());
		appointment.setAppointmentNo(this.getAppointmentNo());
		appointment.setAppointmentStatus(this.getAppointmentStatus());
		appointment.setCancellationReason(this.getCancellationReason());
		if (this.getCustomerId() != null && this.getCustomerId() > 0) {
			Customer customer = new Customer();
			customer.setId(this.getCustomerId());
			appointment.setCustomer(customer);
		}
		if (this.getEmployeeId() != null && this.getEmployeeId() > 0) {
			Employee employee = new Employee();
			employee.setId(this.getEmployeeId());
			appointment.setEmployee(employee);
		}

		appointment.setHoursSpent(this.getHoursSpent());
		appointment.setId(this.getId());
		appointment.setLastModifiedDate(this.getLastModifiedDate());
		appointment.setLastModifiedUser(this.getLastModifiedUser());
		appointment.setPayableAmount(this.getPayableAmount());
		appointment.setPaymentStatus(this.getPaymentStatus());
		appointment.setRemarks(this.getRemarks());

		if(!CommonUtils.isEmpty(this.getStartDateFilter())) {
			try {
				appointment.setStartDate(CommonUtils.parseDBDate(this.getStartDateFilter()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.getStartDate() != null) {
			appointment.setStartDate(this.getStartDate());
		}
		if(!CommonUtils.isEmpty(this.getEndDateFilter())) {
			try {
				appointment.setEndDate(CommonUtils.parseDBDate(this.getEndDateFilter()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.getEndDate() != null) {
			appointment.setEndDate(this.getEndDate());
		}
		if (!CommonUtils.isEmpty(this.getStartTime())) {
			try {
				appointment.setStartDate(CommonUtils.addTimeStringToDate(
						this.getAppointmentDate(), this.getStartTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				appointment.setStartDate(this.getAppointmentDate());
			}
		}

		if (!CommonUtils.isEmpty(this.getEndTime())) {
			try {
				appointment.setEndDate(CommonUtils.addTimeStringToDate(
						this.getAppointmentDate(), this.getEndTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				appointment.setEndDate(this.getAppointmentDate());
			}
		}

		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setId(this.getCustomerAddressId());
		appointment.setCustomerAddress(customerAddress);

		// Set Payment Details
		AppointmentPayment appointmentPayment = new AppointmentPayment();
		appointmentPayment.setAmountPaid(appointment.getPayableAmount());
		appointmentPayment.setPaymentMode(this.getPaymentType());
		appointment.setAppointmentPayment(appointmentPayment);
		// Set Invoice Details
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo(this.getInvoiceNo());
		invoice.setInoviceDate(this.getInvoiceDate());
		appointment.setInvoice(invoice);
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

	public Long getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(Long customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getStartDateStr() {
		if (this.getStartDate() != null) {
			try {
				return CommonUtils.getJavaScriptDate(this.getStartDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getEndDateStr() {
		if (this.getEndDate() != null) {
			try {
				return CommonUtils.getJavaScriptDate(this.getEndDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(String customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public String getAddressRemarks() {
		return addressRemarks;
	}

	public void setAddressRemarks(String addressRemarks) {
		this.addressRemarks = addressRemarks;
	}

	public String getStartDateFilter() {
		return startDateFilter;
	}

	public void setStartDateFilter(String startDateFilter) {
		this.startDateFilter = startDateFilter;
	}

	public String getEndDateFilter() {
		return endDateFilter;
	}

	public void setEndDateFilter(String endDateFilter) {
		this.endDateFilter = endDateFilter;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	
}
