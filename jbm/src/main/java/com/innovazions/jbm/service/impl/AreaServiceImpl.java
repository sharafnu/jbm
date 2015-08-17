package com.innovazions.jbm.service.impl;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.AreaDAO;
import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDAO areaDAO;

	@Override
	public void createArea(Area area) {
		areaDAO.createArea(area);
	}

	@Override
	public List<Area> getAreaList(Area area) {
		return areaDAO.getAreaList(area);
	}

	@Override
	public List<City> getAllCities() {
		return areaDAO.getAllCities();
	}	
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.AreaDAO;
import com.innovazions.jbm.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDAO areaDAO;

>>>>>>> refs/remotes/origin/master
	
}
