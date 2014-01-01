package com.innovazions.jbm.entity;

import java.io.Serializable;


/**
 * The persistent class for the area database table.
 * 
 */
public class Area extends CoreEntity implements Serializable {
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


}