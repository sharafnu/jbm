package com.innovazions.jbm.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.PropertiesUtil;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.SMSNotificationService;

@org.springframework.stereotype.Service
@Qualifier("smsNotificationServiceHTTPImpl")
public class SMSNotificationServiceHTTPImpl implements SMSNotificationService,
		JBMConstants {

	private static String smsHttpUrl;
	private static String smsHttpUserName;
	private static String smsHttpPassword;
	private static String smsHttpEncoding;
	private static String smsHttpDataStringTemplate;

	public static final String DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM_AM_PM = "dd/MM/yyyy HH:mm a";

	// SMS Templates
	private static String smsTemplateAddAppointment;
	private static String smsTemplateCompleteAppointment;
	private static String smsTemplateUpdateAppointment;
	private static String smsTemplateCancelAppointment;
	
	@PostConstruct
	private void initSMSNotificationService() {
		// Initialize the SMS Gateway connection
		smsHttpUrl = PropertiesUtil.getProperty(PROP_SMS_HTTP_URL);
		smsHttpUserName = PropertiesUtil.getProperty(PROP_SMS_HTTP_USERNAME);
		smsHttpPassword = PropertiesUtil.getProperty(PROP_SMS_HTTP_PASSWORD);
		smsHttpEncoding = PropertiesUtil.getProperty(PROP_SMS_HTTP_ENCODING);
		smsHttpDataStringTemplate = PropertiesUtil
				.getProperty(PROP_SMS_HTTP_DATA_STR_TEMPLATE);

		smsTemplateAddAppointment = PropertiesUtil
				.getProperty(PROP_SMS_APPOINTMENT_CREATE_CONTENT);
		
		smsTemplateCompleteAppointment = PropertiesUtil
				.getProperty(PROP_SMS_APPOINTMENT_COMPLETE_CONTENT);
		
		smsTemplateUpdateAppointment = PropertiesUtil
				.getProperty(PROP_SMS_APPOINTMENT_UPDATE_CONTENT);
		
		smsTemplateCancelAppointment = PropertiesUtil
				.getProperty(PROP_SMS_APPOINTMENT_CANCEL_CONTENT);
		
		
		
	}

	@Override
	@Async
	public void sendAppoinmentCreationSMSNotification(Appointment appointment) {

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				System.out.println("Sending SMS");
				String message = String
						.format(smsTemplateAddAppointment.replaceAll(
								"\\{\\d\\}", "%s"),
								CommonUtils.getFormattedDate(
										appointment.getStartDate(),
										DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM_AM_PM));

				String response = sendSMS(message, "+971"
						+ appointment.getCustomer().getMobile1());

				System.out.println("Message Sent Successfully..:" + response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can't send SMS, Mobile number not found !");
		}

	}

	@Override
	@Async
	public void sendAppoinmentCancellationSMSNotification(
			Appointment appointment) {

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				String smsContent = PropertiesUtil
						.getProperty(PROP_SMS_APPOINTMENT_CANCEL_CONTENT);
				System.out
						.println("sendAppoinmentCancellationSMSNotification() > This feature is disabled..");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can't send SMS, Mobile number not found !");
		}

	}

	@Override
	@Async
	public void sendAppoinmentCompletionSMSNotification(Appointment appointment) {

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				
				System.out.println("Sending SMS");
				String message = smsTemplateCompleteAppointment;
/*				String message = String
						.format(smsTemplateCompleteAppointment.replaceAll(
								"\\{\\d\\}", "%s"),
								CommonUtils.getFormattedDate(
										appointment.getStartDate(),
										DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM_AM_PM));*/

				String response = sendSMS(message, "+971"
						+ appointment.getCustomer().getMobile1());

				System.out.println("Message Sent Successfully..:" + response);
				
				System.out
						.println("sendAppoinmentCompletionSMSNotification() > This feature is disabled..");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can't send SMS, Mobile number not found !");
		}

	}

	@Override
	@Async
	public void sendAppoinmentUpdateSMSNotification(Appointment appointment) {

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				System.out.println("Sending SMS");
				String smsContent = PropertiesUtil
						.getProperty(PROP_SMS_APPOINTMENT_UPDATE_CONTENT);
				System.out
						.println("sendAppoinmentUpdateSMSNotification() > This feature is disabled..");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can't send SMS, Mobile number not found !");
		}
	}

	private String getDataString(String messageContent,
			String destinationAddress) throws UnsupportedEncodingException {

		String x = String.format(
				smsHttpDataStringTemplate.replaceAll("\\{\\d\\}", "%s"),
				URLEncoder.encode(smsHttpUserName, smsHttpEncoding),
				URLEncoder.encode(smsHttpPassword, smsHttpEncoding),
				URLEncoder.encode(messageContent, smsHttpEncoding),
				URLEncoder.encode(destinationAddress, smsHttpEncoding));
		return x;

	}

	public String sendSMS(String messageContent, String destinationAddress) {
		String response = null;
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		URLConnection conn = null;
		try {
			// Send data
			URL url = new URL(smsHttpUrl);

			conn = url.openConnection();
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(getDataString(messageContent, destinationAddress));
			wr.flush();

			// Get the response
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Print the response output...
				response = line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wr.close();
				rd.close();
				if (conn != null) {
					conn = null;
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return response;
	}

	public static void main(String[] args) {
		SMSNotificationServiceHTTPImpl notificationServiceHTTPImpl = new SMSNotificationServiceHTTPImpl();
		String messageTemplate = "Your appointment is scheduled on {1}. For cancellation please call 8006296. Thank you for using MaxMaid Services. www.facebook.com/maxmaidcs";
		String message = String.format(
				messageTemplate.replaceAll("\\{\\d\\}", "%s"),
				"02/08/2014 10:00 AM");
		System.out.println(message.length());
		String response = notificationServiceHTTPImpl.sendSMS(message,
				"+971555653982");
		System.out.println(response);
	}// 0|IN_PROGRESS|578329196
}