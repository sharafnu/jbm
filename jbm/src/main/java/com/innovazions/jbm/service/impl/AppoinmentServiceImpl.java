package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.AppointmentDAO;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.AppoinmentService;

@Service
public class AppoinmentServiceImpl implements AppoinmentService {

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Override
	public Long createAppointment(Appointment appointment) {
		return appointmentDAO.createAppointment(appointment);
	}

	@Override
	public List<Appointment> getAppointmentListByFilter(Appointment appointment) {
		return appointmentDAO.getAppointmentListByFilter(appointment);
	}

}
