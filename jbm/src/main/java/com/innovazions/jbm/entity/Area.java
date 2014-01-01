package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.innovazions.jbm.view.AreaView;

/**
 * The persistent class for the area database table.
 * 
 */
public class Area extends CoreEntity<Area, AreaView> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private City city;

	public Area() {
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

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public AreaView convertEntityToView() {
		AreaView areaView = new AreaView();
		areaView.setAreaId(this.getId());
		areaView.setAreaName(this.getName());
		if (this.getCity() != null) {
			areaView.setCityId(this.getCity().getId());
			areaView.setCityName(this.getCity().getName());
		}
		return areaView;
	}

	@Override
	public List<AreaView> convertEntitiesToViews(List<Area> areaList) {
		List<AreaView> areaViewList = new ArrayList<AreaView>();
		for (Area area : areaList) {
			areaViewList.add(area.convertEntityToView());
		}
		return areaViewList;
	}
}