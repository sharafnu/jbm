package com.innovazions.jbm.service;

import org.springframework.scheduling.annotation.Async;

import com.innovazions.jbm.entity.Appointment;

public interface SMSNotificationService {

	@Async
	public void sendAppoinmentCreationSMSNotification(Appointment appointment);

	@Async
	public void sendAppoinmentCompletionSMSNotification(
			Appointment appointment);
	
	@Async
	public void sendAppoinmentCancellationSMSNotification(
			Appointment appointment);
	
	@Async
	public void sendAppoinmentUpdateSMSNotification(
			Appointment appointment);
}
