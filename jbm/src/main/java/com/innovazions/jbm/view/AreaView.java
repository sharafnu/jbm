package com.innovazions.jbm.view;

import java.util.List;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;

public class AreaView extends GenericView<AreaView, Area> {

	private Long areaId;

	private String areaName;

	private Long cityId;

	private String cityName;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public Area convertViewToEntity() {
		Area area = new Area();
		area.setId(this.getAreaId());
		area.setName(this.getAreaName());
		City city = new City();
		city.setId(this.getCityId());
		area.setCity(city);
		return area;
	}

	@Override
	public List<Area> convertViewsToEntities(List<AreaView> areaViewList) {
		// TODO Auto-generated method stub
		return null;
	}
}
