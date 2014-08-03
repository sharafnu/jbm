package com.innovazions.jbm.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.GatewayException;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;
import org.smslib.TimeoutException;
import org.smslib.smpp.BindAttributes;
import org.smslib.smpp.BindAttributes.BindType;
import org.smslib.smpp.jsmpp.JSMPPGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.PropertiesUtil;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.SMSNotificationService;

@org.springframework.stereotype.Service
@Qualifier("smsNotificationServiceSMPPImpl")
public class SMSNotificationServiceSMPPImpl implements SMSNotificationService,
		JBMConstants {

	@Autowired
	private JavaMailSender mailSender;

	// TODO : Add SMSNotificationDAO here

	private void initSMSNotificationService() {
		// Initialize the SMS Gateway connection
		try {
			String smppAccountName = PropertiesUtil
					.getProperty(PROP_SMS_SMPP_ACCOUNT_ID);
			String smppHost = PropertiesUtil.getProperty(PROP_SMS_SMPP_HOST);
			String smppPort = PropertiesUtil.getProperty(PROP_SMS_SMPP_PORT);
			String smppUserId = PropertiesUtil
					.getProperty(PROP_SMS_SMPP_USER_ID);
			String smppPassword = PropertiesUtil
					.getProperty(PROP_SMS_SMPP_PASSWORD);
			String smppAccountType = PropertiesUtil
					.getProperty(PROP_SMS_SMPP_ACCOUNT_TYPE);

			/*
			 * JSMPPGateway gateway = new JSMPPGateway("smppcon", "localhost",
			 * 2715, new BindAttributes("smppclient1", "password", "cp",
			 * BindType.TRANSCEIVER));
			 */

			JSMPPGateway gateway = new JSMPPGateway(smppAccountName, smppHost,
					Integer.parseInt(smppPort),
					new BindAttributes(smppUserId, smppPassword,
							smppAccountType, BindType.TRANSCEIVER));

			org.smslib.Service.getInstance().addGateway(gateway);

			Service.getInstance().setInboundMessageNotification(
					new InboundNotification());
			Service.getInstance().setGatewayStatusNotification(
					new GatewayStatusNotification());
			Service.getInstance().setOutboundMessageNotification(
					new OutboundNotification());
			Service.getInstance().startService();
			System.out.println("SMS Services Started..");

		} catch (GatewayException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (SMSLibException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Async
	public void sendAppoinmentCreationSMSNotification(Appointment appointment) {

		if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED) {
			initSMSNotificationService();
		}

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				System.out.println("Sending SMS");
				String smsContent = PropertiesUtil
						.getProperty(PROP_SMS_APPOINTMENT_CREATE_CONTENT);
				System.out.println("smsContent : " + smsContent);
				/*
				 * smsContent = smsContent.replace("{customer_name}",
				 * appointment .getCustomer().getFullName()); smsContent =
				 * smsContent.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); smsContent =
				 * smsContent.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				OutboundMessage msg = new OutboundMessage(appointment
						.getCustomer().getMobile1(), smsContent);
				// Request Delivery Report
				msg.setStatusReport(true);
				// Retry in case of exceptions
				try {
					//Service.getInstance().sendMessage(msg);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Retrying..");
					//Service.getInstance().startService();
					//Service.getInstance().sendMessage(msg);
				}

				System.out.println("Message Sent Successfully..");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can't send SMS, Mobile number not found !");
		}

	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process(AGateway gateway, OutboundMessage msg) {
			System.out.println("Outbound handler called from Gateway: "
					+ gateway.getGatewayId());
			System.out.println(msg);
		}
	}

	public class InboundNotification implements IInboundMessageNotification {
		public void process(AGateway gateway, MessageTypes msgType,
				InboundMessage msg) {
			if (msgType == MessageTypes.INBOUND)
				System.out
						.println(">>> New Inbound message detected from Gateway: "
								+ gateway.getGatewayId());
			else if (msgType == MessageTypes.STATUSREPORT)
				System.out
						.println(">>> New Inbound Status Report message detected from Gateway: "
								+ gateway.getGatewayId());
			System.out.println(msg);
		}
	}

	public class GatewayStatusNotification implements
			IGatewayStatusNotification {
		public void process(AGateway gateway, GatewayStatuses oldStatus,
				GatewayStatuses newStatus) {
			System.out.println(">>> Gateway Status change for "
					+ gateway.getGatewayId() + ", OLD: " + oldStatus
					+ " -> NEW: " + newStatus);
		}
	}

	public static void main(String[] args) throws ParseException {
		String userTime = "02:22:03 PM";
		Date userDate = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");

		Calendar userDateCal = Calendar.getInstance();
		userDateCal.setTime(userDate);

		Calendar userTimeCal = Calendar.getInstance();
		userTimeCal.setTime(dateFormat.parse(userTime));

		System.out.println(userDateCal.getTime());

		userDateCal.set(Calendar.HOUR, userTimeCal.get(Calendar.HOUR));
		userDateCal.set(Calendar.MINUTE, userTimeCal.get(Calendar.MINUTE));
		userDateCal.set(Calendar.SECOND, userTimeCal.get(Calendar.SECOND));
		userDateCal.set(Calendar.AM_PM, userTimeCal.get(Calendar.AM_PM));
		System.out.println(userDateCal.getTime());
	}

	@Override
	@Async
	public void sendAppoinmentCancellationSMSNotification(
			Appointment appointment) {

		if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED) {
			initSMSNotificationService();
		}

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				System.out.println("Sending SMS");
				String smsContent = PropertiesUtil
						.getProperty(PROP_SMS_APPOINTMENT_CANCEL_CONTENT);
				/*
				 * smsContent = smsContent.replace("{customer_name}",
				 * appointment .getCustomer().getFullName()); smsContent =
				 * smsContent.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); smsContent =
				 * smsContent.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				OutboundMessage msg = new OutboundMessage(appointment
						.getCustomer().getMobile1(), smsContent);
				// Request Delivery Report
				msg.setStatusReport(true);
				// Retry in case of exceptions
				try {
					//Service.getInstance().sendMessage(msg);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Retrying..");
					//Service.getInstance().startService();
					//Service.getInstance().sendMessage(msg);
				}

				System.out.println("Message Sent Successfully..");
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

		if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED) {
			initSMSNotificationService();
		}

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				System.out.println("Sending SMS");
				String smsContent = PropertiesUtil
						.getProperty(PROP_SMS_APPOINTMENT_COMPLETE_CONTENT);
				/*
				 * smsContent = smsContent.replace("{customer_name}",
				 * appointment .getCustomer().getFullName()); smsContent =
				 * smsContent.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); smsContent =
				 * smsContent.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				OutboundMessage msg = new OutboundMessage(appointment
						.getCustomer().getMobile1(), smsContent);
				// Request Delivery Report
				msg.setStatusReport(true);
				// Retry in case of exceptions
				try {
					//Service.getInstance().sendMessage(msg);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Retrying..");
					//Service.getInstance().startService();
					//Service.getInstance().sendMessage(msg);
				}

				System.out.println("Message Sent Successfully..");
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

		if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED) {
			initSMSNotificationService();
		}

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getMobile1())) {
			try {
				System.out.println("Sending SMS");
				String smsContent = PropertiesUtil
						.getProperty(PROP_SMS_APPOINTMENT_UPDATE_CONTENT);
				System.out.println("smsContent : " + smsContent);
				/*
				 * smsContent = smsContent.replace("{customer_name}",
				 * appointment .getCustomer().getFullName()); smsContent =
				 * smsContent.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); smsContent =
				 * smsContent.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				OutboundMessage msg = new OutboundMessage(appointment
						.getCustomer().getMobile1(), smsContent);
				// Request Delivery Report
				msg.setStatusReport(true);
				// Retry in case of exceptions
				try {
					//Service.getInstance().sendMessage(msg);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Retrying..");
					//Service.getInstance().startService();
					//Service.getInstance().sendMessage(msg);
				}

				System.out.println("Message Sent Successfully..");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Can't send SMS, Mobile number not found !");
		}
	}
}