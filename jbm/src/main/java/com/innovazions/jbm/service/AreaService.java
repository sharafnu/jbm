package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;

public interface AreaService {
	
	public void createArea(Area area);
	
	public List<Area> getAreaList(Area area);
	
	public List<City> getAllCities();
}
