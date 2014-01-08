package com.innovazions.jbm.entity;

import java.util.List;

public class SystemProperty extends CoreEntity<SystemProperty, SystemProperty> {

	private static final long serialVersionUID = 1L;

	private long id;

	private String propKey;

	private String propValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "System Property [Key=" + getPropKey() + ", id=" + getId()
				+ ", Value=" + getPropValue() + "]";
	}

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	@Override
	public SystemProperty convertEntityToView() {
		return this;
	}

	@Override
	public List<SystemProperty> convertEntitiesToViews(
			List<SystemProperty> entityList) {
		return entityList;
	}

}