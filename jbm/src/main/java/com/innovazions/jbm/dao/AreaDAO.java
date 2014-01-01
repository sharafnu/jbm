package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;

public interface AreaDAO {

	public long createArea(Area area);

	public long updateArea(Area area);

	public long deleteArea(Area area);

	public List<Area> getAreaList(Area area);

	public List<City> getAllCities();
}
