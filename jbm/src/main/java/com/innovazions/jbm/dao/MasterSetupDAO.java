package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.MasterSetup;

public interface MasterSetupDAO {

	public long createMasterSetup(MasterSetup masterSetup);

	public void updateMasterSetup(MasterSetup masterSetup);

	public void deleteMasterSetup(MasterSetup masterSetup);

	public List<MasterSetup> getMasterSetupList(MasterSetup masterSetup);
	}
