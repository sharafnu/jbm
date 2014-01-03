package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.Appointment;

public interface AppointmentDAO {

	public long createAppointment(Appointment Appointment);

	public long updateAppointment(Appointment Appointment);

	public long deleteAppointment(Appointment Appointment);

	public List<Appointment> getAppointmentListByFilter(Appointment Appointment);

}
