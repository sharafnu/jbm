package com.innovazions.jbm.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

	public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";

	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String TIME_FORMAT_HHMMSS_AM_PM = "hh:mm:ss a";

	public static final String DB_DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

	public static final String DB_DATE_TIME_FORMAT_YYYYMMDD = "yyyy-MM-dd HH:mm:ss";

	public static String getDBDate(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(DB_DATE_FORMAT_YYYYMMDD);
		return dateFormat.format(inputDate);
	}

	public static Timestamp getTimeStampFromDate(Date inputDate) {
		return new Timestamp(inputDate.getTime());
	}

	public static String getDBDateTime(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(
				DB_DATE_TIME_FORMAT_YYYYMMDD);
		return dateFormat.format(inputDate);
	}

	public static String getFormattedDate(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DDMMYYYY);
		return dateFormat.format(inputDate);
	}

	public static String getFormattedDateTime(Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat(
				DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(inputDate);
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
}
