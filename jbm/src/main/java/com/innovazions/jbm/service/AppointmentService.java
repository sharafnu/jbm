package com.innovazions.jbm.service;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.vo.StaffAppointmentCountVO;

public interface AppointmentService {

	public Long createAppointment(Appointment appointment);

	public List<Appointment> getAppointmentListByFilter(Appointment appointment);

	public Appointment getAppoinmentDetailsByAppoinmentId(Long appointmentId);

	public List<Appointment> getAllAppointmentComboList();

	public void updateAppointment(Appointment appointment);

	public List<Appointment> getPendingAppointmentComboList();

	public List<StaffAppointmentCountVO> getAllStaffAppointmentCountListByDate(
			Date appointmentDate);
}
