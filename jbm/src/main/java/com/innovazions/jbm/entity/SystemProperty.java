package com.innovazions.jbm.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.innovazions.jbm.view.MasterSetupView;
import com.innovazions.jbm.view.SystemPropertyView;

public class SystemProperty extends
		CoreEntity<SystemProperty, SystemPropertyView> {

	private static final long serialVersionUID = 1L;

	private long id;

	private String propGroup;

	private String propKey;

	private String propValue;

	private String description;

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
	public SystemPropertyView convertEntityToView() {
		SystemPropertyView systemPropertyView = new SystemPropertyView();

		systemPropertyView.setDescription(this.getDescription());
		systemPropertyView.setId(this.getId());
		systemPropertyView.setLastModifiedDate(this.getLastModifiedDate());
		systemPropertyView.setLastModifiedUser(this.getLastModifiedUser());
		systemPropertyView.setPropGroup(this.getPropGroup());
		systemPropertyView.setPropKey(this.getPropKey());
		systemPropertyView.setPropValue(this.getPropValue());
		return systemPropertyView;
	}

	@Override
	public List<SystemPropertyView> convertEntitiesToViews(
			List<SystemProperty> systemPropertyList) {
		List<SystemPropertyView> systemPropertyViewList = new ArrayList<SystemPropertyView>();
		for (SystemProperty systemProperty : systemPropertyList) {
			systemPropertyViewList.add(systemProperty.convertEntityToView());
		}
		return systemPropertyViewList;
	}

	public String getPropGroup() {
		return propGroup;
	}

	public void setPropGroup(String propGroup) {
		this.propGroup = propGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}