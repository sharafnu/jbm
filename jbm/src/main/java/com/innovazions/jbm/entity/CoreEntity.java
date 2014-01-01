package com.innovazions.jbm.entity;

import java.util.Date;
import java.util.List;

public abstract class CoreEntity<Entity, View> {

	public abstract View convertEntityToView();

	public abstract List<View> convertEntitiesToViews(List<Entity> entityList);
	
	private Date lastModifiedDate;

	private String lastModifiedUser;

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
}
