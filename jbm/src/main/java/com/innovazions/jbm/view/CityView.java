package com.innovazions.jbm.view;

import java.util.List;

import com.innovazions.jbm.entity.City;

public class CityView extends GenericView<CityView, City> {

	private Long cityId;
	
	private String cityName;
	
	@Override
	public City convertViewToEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> convertViewsToEntities(List<CityView> cityViewList) {
		// TODO Auto-generated method stub
		return null;
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

}
