package com.innovazions.jbm.dao;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.vo.StaffAppointmentCountVO;

public interface AppointmentDAO {

	public long createAppointment(Appointment Appointment);

	public long updateAppointment(Appointment Appointment);

	public long deleteAppointment(Appointment Appointment);

	public List<Appointment> getAppointmentListByFilter(Appointment Appointment);

	public Appointment getAppoinmentDetailsByAppoinmentId(Long appoinmentId);

	public List<Appointment> getAllAppointmentComboList();

	public List<Appointment> getPendingAppointmentComboList();
	
	public List<StaffAppointmentCountVO> getAllStaffAppointmentCountListByDate(Date appointmentDate);
}
