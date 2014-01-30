package com.innovazions.jbm.service;

import com.innovazions.jbm.entity.Appointment;

public interface SMSNotificationService {

	public Long sendAppoinmentCreationSMSNotification(Appointment appointment);

	public void sendAppoinmentCancellationSMSNotification(
			Appointment appointment);
}
