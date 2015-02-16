package com.innovazions.jbm.dao;

import java.util.List;

import javax.sql.DataSource;

import com.innovazions.jbm.entity.SystemProperty;

public interface CommonDAO {


	List<SystemProperty> getAllSystemProperties();

	String getSequenceCodeByType(String type, String prefixPropName);

	void deleteSystemProperty(SystemProperty systemProperty);

	void updateSystemProperty(SystemProperty systemProperty);

	long createSystemProperty(SystemProperty systemProperty);
	
	DataSource getDataSource();
}
