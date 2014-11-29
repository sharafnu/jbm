package com.innovazions.jbm.service;

import java.util.Date;	
import java.util.List;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.vo.CalendarAppointmentDetailCalendarVO;
import com.innovazions.jbm.vo.DailyAppointmentCountVO;
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

	public List<DailyAppointmentCountVO> getDailyAppointmentCountList();

	public List<DailyAppointmentCountVO> getDailyAppointmentCountListByStaffId(
			Long staffId);

	List<Appointment> getStaffAppointmentsBetweenDates(Long staffId,
			Date fromDateTime, Date toDateTime);

	public List<DailyAppointmentCountVO> getStaffAppointmentsTimeBreakups(
			Date appointmentDate);

	public List<CalendarAppointmentDetailCalendarVO> getAppointmentDetailsForCalendarByDate(
			Date appointmentDate);

	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendarByDate(
			Date appointmentDate);

	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendarBetweenDate(
			Date startDate, Date endDate);

	public boolean isDuplicateInvoiceNo(String invoiceNo);

	public List<Appointment> getCustomerCancelledAppointmentBetweenDates(
			long customerId, Date startDate, Date endDate);

	public List<Appointment> findCustomerAppointmentByAddressAndDate(
			Long customerAddressId, Date startDate, Date endDate);

	public List<Appointment> getStaffAppointmentsBetweenDatesNotId(Long staffId,
			Date fromDateTime, Date toDateTime, Long id);
	
	public void modifyAppointmentDetails(Appointment appointment);

	public List<Appointment> getCustomerAddressAppointmentsBetweenDates(
			Long customerAddressId, Date startDate,
			Date endDate);
	
	public List<Appointment> getCustomerAddressAppointmentsBetweenDatesNotId(
			Long customerAddressId, Date startDate,
			Date endDate,Long id);
	
	public List<Appointment> getActiveAppointmentComboList();
	
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendar();

	List<Appointment> getAppointmentDetailedListByFilter(Appointment appointment); 
}
