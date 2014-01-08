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

}
