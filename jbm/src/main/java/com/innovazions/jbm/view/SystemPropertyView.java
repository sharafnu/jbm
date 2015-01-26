package com.innovazions.jbm.view;
	
import java.util.ArrayList;
import java.util.List;

import com.innovazions.jbm.entity.SystemProperty;

public class SystemPropertyView extends GenericView<SystemPropertyView, SystemProperty> {

	private long id;

	private String propGroup;
	
	private String propKey;

	private String propValue;
	
	private String description;

	@Override
	public SystemProperty convertViewToEntity() {
		SystemProperty systemProperty = new SystemProperty();
		systemProperty.setId(this.getId());
		systemProperty.setDescription(this.getDescription());
		systemProperty.setLastModifiedDate(this.getLastModifiedDate());
		systemProperty.setLastModifiedUser(this.getLastModifiedUser());
		systemProperty.setPropGroup(this.getPropGroup());
		systemProperty.setPropKey(this.getPropKey());
		systemProperty.setPropValue(this.getPropValue());
		return systemProperty;
	}

	@Override
	public List<SystemProperty> convertViewsToEntities(
			List<SystemPropertyView> systemPropertyViews) {
		List<SystemProperty> systemPropertyList = new ArrayList<SystemProperty>();
		for (SystemPropertyView systemPropertyView : systemPropertyViews) {
			systemPropertyList.add(systemPropertyView.convertViewToEntity());
		}
		return systemPropertyList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropGroup() {
		return propGroup;
	}

	public void setPropGroup(String propGroup) {
		this.propGroup = propGroup;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
