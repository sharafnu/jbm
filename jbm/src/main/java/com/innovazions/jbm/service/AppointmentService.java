package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.Appointment;

public interface AppointmentService {

	public Long createAppointment(Appointment appointment);

	public List<Appointment> getAppointmentListByFilter(Appointment appointment);

	public Appointment getAppoinmentDetailsByAppoinmentId(Long appointmentId);
}
