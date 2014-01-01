package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.innovazions.jbm.view.CityView;

/**
 * The persistent class for the city database table.
 * 
 */
public class City extends CoreEntity<City, CityView> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	public City() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public CityView convertEntityToView() {
		CityView cityView = new CityView();
		cityView.setCityId(this.getId());
		cityView.setCityName(this.getName());
		return cityView;
	}

	@Override
	public List<CityView> convertEntitiesToViews(List<City> cityEntityList) {
		List<CityView> cityViewList = new ArrayList<CityView>();
		for (City city : cityEntityList) {
			cityViewList.add(city.convertEntityToView());
		}
		return cityViewList;
	}

}