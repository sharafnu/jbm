package com.innovazions.jbm.service;

import com.innovazions.jbm.entity.Appointment;

public interface EmailNotificationService {

	public Long sendAppoinmentCreationEmailNotification(Appointment appointment);
	
	public Long sendAppoinmentCancellationEmailNotification(Appointment appointment);
}
