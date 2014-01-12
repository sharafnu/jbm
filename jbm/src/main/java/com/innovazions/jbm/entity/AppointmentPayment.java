package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the appointment_payment database table.
 * 
 */
public class AppointmentPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private double amountPaid;

	private String paymentMode;

	private Appointment appointment;

	public AppointmentPayment() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

}