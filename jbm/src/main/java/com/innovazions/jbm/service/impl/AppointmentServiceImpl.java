package com.innovazions.jbm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.dao.AppointmentDAO;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.AppointmentService;
import com.innovazions.jbm.vo.CalendarAppointmentDetailCalendarVO;
import com.innovazions.jbm.vo.DailyAppointmentCountVO;
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
		if (appointment.getAppointmentStatus().equals(
				JBMConstants.APPOINTMENT_STATUS_COMPLETED)) {
			appointmentDAO.updateAppointment(appointment);
		} else if (appointment.getAppointmentStatus().equals(
				JBMConstants.APPOINTMENT_STATUS_CANCELLED)) {
			appointmentDAO.cancelAppointment(appointment);
		}

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

	@Override
	public List<DailyAppointmentCountVO> getDailyAppointmentCountList() {
		return appointmentDAO.getDailyAppointmentCountList();
	}

	@Override
	public List<DailyAppointmentCountVO> getDailyAppointmentCountListByStaffId(
			Long staffId) {
		return appointmentDAO.getDailyAppointmentCountListByStaffId(staffId);
	}

	@Override
	public List<Appointment> getStaffAppointmentsBetweenDates(Long staffId,
			Date fromDateTime, Date toDateTime) {
		return appointmentDAO.getStaffAppointmentsBetweenDates(staffId,
				fromDateTime, toDateTime);
	}

	@Override
	public List<DailyAppointmentCountVO> getStaffAppointmentsTimeBreakups(
			Date appointmentDate) {
		return appointmentDAO.getStaffAppointmentsTimeBreakups(appointmentDate);
	}

	@Override
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentDetailsForCalendarByDate(
			Date appointmentDate) {
		return appointmentDAO
				.getAppointmentDetailsForCalendarByDate(appointmentDate);
	}

	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendarByDate(
			Date appointmentDate) {
		return appointmentDAO
				.getAppointmentStaffNameForCalendarByDate(appointmentDate);
	}

	@Override
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendarBetweenDate(
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return appointmentDAO.getAppointmentStaffNameForCalendarBetweenDate(
				startDate, endDate);
	}

	public boolean isDuplicateInvoiceNo(String invoiceNo) {
		return appointmentDAO.isDuplicateInvoiceNo(invoiceNo);
	}

	@Override
	public List<Appointment> getCustomerCancelledAppointmentBetweenDates(
			long customerId, Date startDate, Date endDate) {
		return appointmentDAO.getCustomerCancelledAppointmentBetweenDates(
				customerId, startDate, endDate);
	}
}
