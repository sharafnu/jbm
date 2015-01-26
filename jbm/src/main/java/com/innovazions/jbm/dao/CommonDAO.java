package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.SystemProperty;

public interface CommonDAO {


	public List<SystemProperty> getAllSystemProperties();

	public String getSequenceCodeByType(String type, String prefixPropName);

	void deleteSystemProperty(SystemProperty systemProperty);

	void updateSystemProperty(SystemProperty systemProperty);

	long createSystemProperty(SystemProperty systemProperty);
}
