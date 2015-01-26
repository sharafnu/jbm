package com.innovazions.jbm.service;

import java.util.List;

import com.innovazions.jbm.entity.MasterSetup;

public interface MasterSetupService {

	public void createMasterSetup(MasterSetup masterSetup);

	public List<MasterSetup> getMasterSetupList(String masterType);

	void updateMasterSetup(MasterSetup masterSetup);

	void deleteMasterSetup(MasterSetup masterSetup);

}
