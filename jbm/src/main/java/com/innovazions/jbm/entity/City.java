package com.innovazions.jbm.entity;

import java.io.Serializable;


/**
 * The persistent class for the city database table.
 * 
 */
public class City implements Serializable {
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

}