package com.innovazions.jbm.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.innovazions.jbm.view.ActionStatus;

public class CommonUtils {

	public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";

	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String TIME_FORMAT_HHMMSS_AM_PM = "hh:mm a";

	public static final String DB_DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

	public static final String DB_DATE_TIME_FORMAT_YYYYMMDD = "yyyy-MM-dd HH:mm:ss";

	public static final String JS_DATE_TIME_FORMAT_YYYYMMDD = "yyyy-MM-dd HH:mm:ss";

	public static final String JS_DATE_FORMAT_YYYYMMDD = "MM-dd-yyyy";

	public static final String JS_DATE_FORMAT_DDMMYYYY = "dd-MM-yyyy";

	public static String getDBDate(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(DB_DATE_FORMAT_YYYYMMDD);
		return dateFormat.format(inputDate);
	}

	public static Date parseDBDate(String inputDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(DB_DATE_FORMAT_YYYYMMDD);
		return dateFormat.parse(inputDate);
	}

	public static Date parseDBDateDDMMYY(String inputDate)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(JS_DATE_FORMAT_DDMMYYYY);
		return dateFormat.parse(inputDate);
	}

	public static Timestamp getTimeStampFromDate(Date inputDate) {
		return new Timestamp(inputDate.getTime());
	}

	public static String getDBDateTime(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(
				DB_DATE_TIME_FORMAT_YYYYMMDD);
		return dateFormat.format(inputDate);
	}

	public static String getFormattedDate(Date inputDate, String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(inputDate);
	}

	public static String getFormattedDate(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DDMMYYYY);
		return dateFormat.format(inputDate);
	}

	public static Date getFormattedDateObj(Date inputDate)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DDMMYYYY);
		String formattedDate = dateFormat.format(inputDate);
		return dateFormat.parse(formattedDate);
	}

	public static String getFormattedDateTime(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(
				DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(inputDate);
	}

	public static Date getDate(String formattedDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(
				DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		return dateFormat.parse(formattedDate);
	}

	public static boolean isEmpty(String inputStr) {
		if (inputStr != null && inputStr.trim().length() > 0) {
			return false;
		}
		return true;
	}

	public static Date getMidnightDate(Date inputDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static String getMidnightDateStr(Date inputDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new SimpleDateFormat().format(calendar.getTime());
	}

	public static Date getEndMidnightDate(Date inputDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static String getEndMidnightDateStr(Date inputDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return new SimpleDateFormat().format(calendar.getTime());
	}

	public static Date addTimeStringToDate(Date inputDate, String timeStr)
			throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				TIME_FORMAT_HHMMSS_AM_PM);

		Calendar convertedDateCal = Calendar.getInstance();
		convertedDateCal.setTime(inputDate);

		Calendar userTimeCal = Calendar.getInstance();
		userTimeCal.setTime(dateFormat.parse(timeStr));

		convertedDateCal.set(Calendar.HOUR, userTimeCal.get(Calendar.HOUR));
		convertedDateCal.set(Calendar.MINUTE, userTimeCal.get(Calendar.MINUTE));
		convertedDateCal.set(Calendar.SECOND, userTimeCal.get(Calendar.SECOND));
		convertedDateCal.set(Calendar.AM_PM, userTimeCal.get(Calendar.AM_PM));

		return convertedDateCal.getTime();
	}

	public static String getTimeStrFromDate(Date inputDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				TIME_FORMAT_HHMMSS_AM_PM);
		return dateFormat.format(inputDate);
	}

	public static void main(String[] args) {
		System.out.println(CommonUtils.getMonthStartDate(2, 2015));
		System.out.println(CommonUtils.getMonthEndDate(2, 2015));
	}

	public static ActionStatus getUnAuthorizedAccessActionStatus() {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_ERROR,
				ActionMessages.STATUS_MESSAGE_UN_AUTHORIZED_ACCESS);
		return actionStatus;
	}

	public static ActionStatus getDataSaveSuccessActionStatus() {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_SUCCESS,
				ActionMessages.STATUS_MESSAGE_DATA_SAVE_SUCCESS);
		return actionStatus;
	}

	public static ActionStatus getDataUpdateSuccessActionStatus() {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_SUCCESS,
				ActionMessages.STATUS_MESSAGE_DATA_UPDATE_SUCCESS);
		return actionStatus;
	}

	public static ActionStatus getDataDeleteSuccessActionStatus() {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_SUCCESS,
				ActionMessages.STATUS_MESSAGE_DATA_DELETE_SUCCESS);
		return actionStatus;
	}

	public static ActionStatus getDataSaveSuccessActionStatus(Long generatedId) {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_SUCCESS,
				ActionMessages.STATUS_MESSAGE_DATA_SAVE_SUCCESS, generatedId);
		return actionStatus;
	}

	public static ActionStatus getDataSaveSuccessActionStatus(Long generatedId,
			String generatedCode) {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_SUCCESS,
				ActionMessages.STATUS_MESSAGE_DATA_SAVE_SUCCESS, generatedId,
				generatedCode);
		return actionStatus;
	}

	public static ActionStatus getSuccessActionStatus(String message) {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_SUCCESS, message);
		return actionStatus;
	}

	public static ActionStatus getErrorActionStatus(String message) {
		ActionStatus actionStatus = new ActionStatus(
				ActionStatus.STATUS_TYPE_ERROR, message);
		return actionStatus;
	}

	public static String getJavaScriptDateTime(Date startDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				JS_DATE_TIME_FORMAT_YYYYMMDD);
		return dateFormat.format(startDate);
	}

	public static String getJavaScriptDate(Date startDate)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				JS_DATE_FORMAT_YYYYMMDD);
		return dateFormat.format(startDate);
	}

	public static boolean isCurrentTimeSlot(Date startDate, Date endDate) {
		Date currentDateTime = getCurrentDate("Asia/Dubai");
		if (currentDateTime.after(startDate) && currentDateTime.before(endDate)) {
			return true;
		}
		return false;
	}

	public static Date getPastDate(Date inputDate, int offsetDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		calendar.add(Calendar.DAY_OF_MONTH, (offsetDays * -1));
		return calendar.getTime();
	}

	public static Date getCurrentDate(String timeZone) {
		Calendar localTime = Calendar.getInstance();
		localTime.setTime(new Date());

		// System.out.printf("Local time  : %02d:%02d:%02d\n", hour, minute,
		// second);

		Calendar dubaiTime = new GregorianCalendar(
				TimeZone.getTimeZone(timeZone));
		dubaiTime.setTimeInMillis(localTime.getTimeInMillis());
		int hour = dubaiTime.get(Calendar.HOUR);
		int minute = dubaiTime.get(Calendar.MINUTE);
		int second = dubaiTime.get(Calendar.SECOND);

		localTime.set(Calendar.HOUR, hour);
		localTime.set(Calendar.MINUTE, minute);
		localTime.set(Calendar.SECOND, second);

		// System.out.printf("India time: %02d:%02d:%02d\n", hour, minute,
		// second);
		return localTime.getTime();

	}
	
	public static Date getMonthStartDate(int month, int year) {
		Date aDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(aDate);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static Date getMonthEndDate(int month, int year) {
		Date monthStartDate = getMonthStartDate(month, year);
		Calendar cal = Calendar.getInstance();
		cal.setTime(monthStartDate);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
}