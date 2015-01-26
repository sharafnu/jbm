package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.SystemProperty;

public interface CommonService {

	public List<SystemProperty> getAllSystemProperties();
	
	public String getSequenceCodeByType(String type, String prefixPropName);

	long createSystemProperty(SystemProperty systemProperty);

	void updateSystemProperty(SystemProperty systemProperty);

	void deleteSystemProperty(SystemProperty systemProperty);
}
