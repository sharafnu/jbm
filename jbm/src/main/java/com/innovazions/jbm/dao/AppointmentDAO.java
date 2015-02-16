package com.innovazions.jbm.dao;

import java.util.Date;
import java.util.List;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.vo.CalendarAppointmentDetailCalendarVO;
import com.innovazions.jbm.vo.DailyAppointmentCountVO;
import com.innovazions.jbm.vo.StaffAppointmentCountVO;

public interface AppointmentDAO {

	public long createAppointment(Appointment Appointment);

	public long updateAppointment(Appointment Appointment);

	public long deleteAppointment(Appointment Appointment);

	public Appointment getAppoinmentDetailsByAppoinmentId(Long appoinmentId);

	public List<Appointment> getAllAppointmentComboList();

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

	/**
	 * @deprecated use {@link #getAppointmentStaffNameForCalendar()} instead 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Deprecated
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendarBetweenDate(
			Date startDate, Date endDate);

	public void cancelAppointment(Appointment appointment);

	public boolean isDuplicateInvoiceNo(String invoiceNo);

	public List<Appointment> getCustomerCancelledAppointmentBetweenDates(
			long customerId, Date startDate, Date endDate);

	public List<Appointment> findCustomerAppointmentByAddressAndDate(
			Long customerAddressId, Date startDate, Date endDate);

	public List<Appointment> getStaffAppointmentsBetweenDatesNotId(
			Long staffId, Date fromDateTime, Date toDateTime, Long id);

	public void modifyAppointmentDetails(Appointment appointment);

	public List<Appointment> getCustomerAddressAppointmentsBetweenDates(
			Long customerAddressId, Date fromDateTime, Date toDateTime);

	public List<Appointment> getCustomerAddressAppointmentsBetweenDatesNotId(
			Long customerAddressId, Date fromDateTime, Date toDateTime, Long id);

	public List<Appointment> getActiveAppointmentComboList();

	List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendar();

	List<Appointment> getAppointmentDetailedListByFilter(Appointment appointment);

	List<Appointment> getAppointmentListByFilter(Appointment appointment, String orderBy, boolean desc);
}
