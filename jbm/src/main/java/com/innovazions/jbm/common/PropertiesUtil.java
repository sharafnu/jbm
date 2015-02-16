package com.innovazions.jbm.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovazions.jbm.entity.SystemProperty;
import com.innovazions.jbm.service.CommonService;

public class PropertiesUtil implements InitializingBean {

	private static Map<String, String> propertiesMap = new HashMap<String, String>();

	@Autowired
	private CommonService commonService;
	
	Logger log = Logger.getLogger(this.getClass().getName());

	public void reloadProperties() throws Exception {
		System.out.println("Re-Loading Properties");
		afterPropertiesSet();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Loading Properties");
		propertiesMap = new HashMap<String, String>();
		List<SystemProperty> systemProperties = commonService
				.getAllSystemProperties();
		for (SystemProperty systemProperty : systemProperties) {
			propertiesMap.put(systemProperty.getPropKey(),
					systemProperty.getPropValue());
		}
		log.info(propertiesMap.size() + " Properties Loaded..");
		//System.out.println(propertiesMap.size() + " Properties Loaded..");
	}

	/**
	 * Return a property loaded by the place holder.
	 * 
	 * @param name
	 *            the property name.
	 * @return the property value.
	 */
	public static String getProperty(final String name) {
		return (String) propertiesMap.get(name);
	}

}