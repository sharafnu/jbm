package com.innovazions.jbm.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.PropertiesUtil;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.EmailNotificationService;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService,
		JBMConstants {

	@Autowired
	private JavaMailSender mailSender;

	// TODO : Add EmailNotificationDAO here

	@Override
	@Async
	public void sendAppoinmentCreationEmailNotification(Appointment appointment) {

		// TODO : Make an entry to database first

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getEmail())) {
			try {
				MimeMessage formattedMessage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(
						formattedMessage, true);
				helper.setFrom(PropertiesUtil
						.getProperty(PROP_EMAIL_NOTIFICATION_FROM_ID));
				helper.setTo(appointment.getCustomer().getEmail());
				String subject = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_CREATE_SUBJECT);
				String mailContents = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_CREATE_CONTENT);
				/*
				 * mailContents = mailContents.replace("{customer_name}",
				 * appointment.getCustomer().getFullName()); mailContents =
				 * mailContents.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); mailContents =
				 * mailContents.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				helper.setSubject(subject);
				helper.setText(mailContents, true);
				//mailSender.send(formattedMessage);
				System.out.println("Email Send Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			// TODO: Update the DB
		} else {
			// Can't send email
			System.out.println("Error : Customer email id not found !");
		}
	
	}

	@Override
	@Async
	public void sendAppoinmentCancellationEmailNotification(
			Appointment appointment) {

		// TODO : Make an entry to database first

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getEmail())) {
			try {
				MimeMessage formattedMessage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(
						formattedMessage, true);
				helper.setFrom(PropertiesUtil
						.getProperty(PROP_EMAIL_NOTIFICATION_FROM_ID));
				helper.setTo(appointment.getCustomer().getEmail());
				String subject = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_CANCEL_SUBJECT);
				String mailContents = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_CANCEL_CONTENT);
				/*
				 * mailContents = mailContents.replace("{customer_name}",
				 * appointment.getCustomer().getFullName()); mailContents =
				 * mailContents.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); mailContents =
				 * mailContents.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				helper.setSubject(subject);
				helper.setText(mailContents, true);
				//mailSender.send(formattedMessage);
				System.out.println("Email Send Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			// TODO: Update the DB
		} else {
			// Can't send email
			System.out.println("Error : Customer email id not found !");
		}
		
	}

	@Override
	@Async
	public void sendAppoinmentCompletionEmailNotification(
			Appointment appointment) {

		// TODO : Make an entry to database first

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getEmail())) {
			try {
				MimeMessage formattedMessage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(
						formattedMessage, true);
				helper.setFrom(PropertiesUtil
						.getProperty(PROP_EMAIL_NOTIFICATION_FROM_ID));
				helper.setTo(appointment.getCustomer().getEmail());
				String subject = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_COMPLETE_SUBJECT);
				String mailContents = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_COMPLETE_CONTENT);
				/*
				 * mailContents = mailContents.replace("{customer_name}",
				 * appointment.getCustomer().getFullName()); mailContents =
				 * mailContents.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); mailContents =
				 * mailContents.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				helper.setSubject(subject);
				helper.setText(mailContents, true);
				//mailSender.send(formattedMessage);
				System.out.println("Email Send Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			// TODO: Update the DB
		} else {
			// Can't send email
			System.out.println("Error : Customer email id not found !");
		}

	}

	@Override
	@Async
	public void sendAppoinmentUpdateEmailNotification(Appointment appointment) {

		// TODO : Make an entry to database first

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getEmail())) {
			try {
				MimeMessage formattedMessage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(
						formattedMessage, true);
				helper.setFrom(PropertiesUtil
						.getProperty(PROP_EMAIL_NOTIFICATION_FROM_ID));
				helper.setTo(appointment.getCustomer().getEmail());
				String subject = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_UPDATE_SUBJECT);
				
				String mailContents = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_UPDATE_CONTENT);
				/*
				 * mailContents = mailContents.replace("{customer_name}",
				 * appointment.getCustomer().getFullName()); mailContents =
				 * mailContents.replace("{appoinment_date}",
				 * CommonUtils.getFormattedDate(appointment
				 * .getAppointmentDate())); mailContents =
				 * mailContents.replace("{location}", appointment
				 * .getCustomerAddress().toString());
				 */
				helper.setSubject(subject);
				helper.setText(mailContents, true);
				//mailSender.send(formattedMessage);
				System.out.println("Email Send Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			// TODO: Update the DB
		} else {
			// Can't send email
			System.out.println("Error : Customer email id not found !");
		}

	}

}
