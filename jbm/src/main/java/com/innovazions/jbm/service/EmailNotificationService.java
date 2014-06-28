package com.innovazions.jbm.service;

import org.springframework.scheduling.annotation.Async;

import com.innovazions.jbm.entity.Appointment;

public interface EmailNotificationService {

	@Async
	public void sendAppoinmentCreationEmailNotification(Appointment appointment);
	
	@Async
	public void sendAppoinmentCompletionEmailNotification(Appointment appointment);
	
	@Async
	public void sendAppoinmentCancellationEmailNotification(Appointment appointment);
	
	@Async
	public void sendAppoinmentUpdateEmailNotification(Appointment appointment);
}
