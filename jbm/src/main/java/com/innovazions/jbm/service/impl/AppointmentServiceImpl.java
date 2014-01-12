package com.innovazions.jbm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.AppointmentDAO;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.AppointmentService;
import com.innovazions.jbm.vo.StaffAppointmentCountVO;

@Service
public class AppointmentServiceImpl implements AppointmentService {

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

	@Override
	public List<Appointment> getAllAppointmentComboList() {
		return appointmentDAO.getAllAppointmentComboList();
	}

	@Override
	public Appointment getAppoinmentDetailsByAppoinmentId(Long appointmentId) {
		return appointmentDAO.getAppoinmentDetailsByAppoinmentId(appointmentId);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		appointmentDAO.updateAppointment(appointment);
	}

	@Override
	public List<Appointment> getPendingAppointmentComboList() {
		return appointmentDAO.getPendingAppointmentComboList();
	}

	@Override
	public List<StaffAppointmentCountVO> getAllStaffAppointmentCountListByDate(
			Date appointmentDate) {
		return appointmentDAO
				.getAllStaffAppointmentCountListByDate(appointmentDate);
	}

}
