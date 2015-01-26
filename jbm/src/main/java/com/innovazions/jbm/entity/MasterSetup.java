package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.innovazions.jbm.view.MasterSetupView;

/**
 * The persistent class for the area database table.
 * 
 */
public class MasterSetup extends CoreEntity<MasterSetup, MasterSetupView>
		implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String masterType;

	private String code;

	private String description;

	@Override
	public MasterSetupView convertEntityToView() {
		MasterSetupView masterSetupView = new MasterSetupView();
		masterSetupView.setId(this.getId());
		masterSetupView.setMasterType(this.getMasterType());
		masterSetupView.setCode(this.getCode());
		masterSetupView.setDescription(this.getDescription());
		return masterSetupView;
	}

	@Override
	public List<MasterSetupView> convertEntitiesToViews(
			List<MasterSetup> masterSetupList) {
		List<MasterSetupView> masterSetupViewList = new ArrayList<MasterSetupView>();
		for (MasterSetup masterSetup : masterSetupList) {
			masterSetupViewList.add(masterSetup.convertEntityToView());
		}
		return masterSetupViewList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMasterType() {
		return masterType;
	}

	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}