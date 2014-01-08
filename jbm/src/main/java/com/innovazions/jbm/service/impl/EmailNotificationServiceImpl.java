package com.innovazions.jbm.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	public Long sendAppoinmentCreationEmailNotification(Appointment appointment) {
		// TODO : Make an entry to database first

		if (appointment != null && appointment.getCustomer() != null
				&& !CommonUtils.isEmpty(appointment.getCustomer().getEmail())) {
			try {
				MimeMessage formattedMessage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(
						formattedMessage, true);
				helper.setFrom(appointment.getCustomer().getEmail());
				helper.setTo(PropertiesUtil
						.getProperty(PROP_EMAIL_NOTIFICATION_FROM_ID));
				String subject = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_CREATE_SUBJECT);
				String mailContents = PropertiesUtil
						.getProperty(PROP_EMAIL_APPOINTMENT_CREATE_CONTENT);
				mailContents = mailContents.replace("{customer_name}",
						appointment.getCustomer().getFullName());
				mailContents = mailContents.replace("{appoinment_date}",
						CommonUtils.getFormattedDate(appointment
								.getAppointmentDate()));
				mailContents = mailContents.replace("{location}", appointment
						.getArea().getName());
				helper.setSubject(subject);
				helper.setText(mailContents, true);
				/*
				 * FileSystemResource footerLogo = new FileSystemResource( new
				 * File( PropertiesUtil
				 * .getProperty(REGISTRTAION_WELCOME_MAIL_FOOTER_LOGO_PATH)));
				 * helper.addInline("footerLogo", footerLogo);
				 */
				mailSender.send(formattedMessage);
				System.out.println("Email Send Successfully");
			} catch (Exception e) {
				// TODO: handle exception
			}

			// TODO: Update the DB
		} else {
			// Can't send email
			System.out.println("Error : Customer email id not found !");
		}
		return null;
	}

}
