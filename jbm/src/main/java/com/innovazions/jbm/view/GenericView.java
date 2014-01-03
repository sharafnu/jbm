package com.innovazions.jbm.view;

import java.util.Date;
import java.util.List;

public abstract class GenericView<View, Entity> {

	private Date lastModifiedDate;

	private String lastModifiedUser;
	
	public abstract Entity convertViewToEntity();

	public abstract List<Entity> convertViewsToEntities(List<View> viewList);

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
