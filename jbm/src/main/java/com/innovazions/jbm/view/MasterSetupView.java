package com.innovazions.jbm.view;
	
import java.util.ArrayList;
import java.util.List;

import com.innovazions.jbm.entity.MasterSetup;

public class MasterSetupView extends GenericView<MasterSetupView, MasterSetup> {

	private Long id;

	private String masterType;

	private String code;

	private String description;

	@Override
	public MasterSetup convertViewToEntity() {
		MasterSetup masterSetup = new MasterSetup();
		masterSetup.setId(this.getId());
		masterSetup.setMasterType(this.getMasterType());
		masterSetup.setCode(this.getCode());
		masterSetup.setDescription(this.getDescription());

		return masterSetup;
	}

	@Override
	public List<MasterSetup> convertViewsToEntities(
			List<MasterSetupView> masterSetupViews) {
		List<MasterSetup> masterSetupList = new ArrayList<MasterSetup>();
		for (MasterSetupView masterSetupView : masterSetupViews) {
			masterSetupList.add(masterSetupView.convertViewToEntity());
		}
		return masterSetupList;
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
