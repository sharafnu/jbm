package com.innovazions.jbm.service.impl;	

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.CommonDAO;
import com.innovazions.jbm.entity.SystemProperty;
import com.innovazions.jbm.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDAO commonDAO;

	@Override
	public List<SystemProperty> getAllSystemProperties() {
		return commonDAO.getAllSystemProperties();
	}

	@Override
	public long createSystemProperty(SystemProperty systemProperty) {
		
		return commonDAO.createSystemProperty(systemProperty);
	}

	@Override
	public void updateSystemProperty(SystemProperty systemProperty) {
		commonDAO.updateSystemProperty(systemProperty);
		
	}

	@Override
	public void deleteSystemProperty(SystemProperty systemProperty) {
		commonDAO.deleteSystemProperty(systemProperty);
	}

	@Override
	public String getSequenceCodeByType(String type, String prefixPropName) {
		return commonDAO.getSequenceCodeByType(type, prefixPropName);
	}

}
	