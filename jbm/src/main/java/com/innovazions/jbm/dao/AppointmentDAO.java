package com.innovazions.jbm.dao;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.vo.DailyAppointmentCountVO;
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

	public List<DailyAppointmentCountVO> getDailyAppointmentCountList();

	public List<DailyAppointmentCountVO> getDailyAppointmentCountListByStaffId(
			Long staffId);

	List<Appointment> getStaffAppointmentsBetweenDates(Long staffId,
			Date fromDateTime, Date toDateTime);
	
	public List<DailyAppointmentCountVO> getStaffAppointmentsTimeBreakups(
			Date appointmentDate);
}
