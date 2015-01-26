package com.innovazions.jbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovazions.jbm.dao.MasterSetupDAO;
import com.innovazions.jbm.entity.MasterSetup;
import com.innovazions.jbm.service.MasterSetupService;

@Service
public class MasterSetupServiceImpl implements MasterSetupService {

	@Autowired
	private MasterSetupDAO masterSetupDAO;

	@Override
	public void createMasterSetup(MasterSetup masterSetup) {
		masterSetupDAO.createMasterSetup(masterSetup);

	}
	
	@Override
	public void updateMasterSetup(MasterSetup masterSetup) {
		masterSetupDAO.updateMasterSetup(masterSetup);

	}
	
	@Override
	public void deleteMasterSetup(MasterSetup masterSetup) {
		masterSetupDAO.deleteMasterSetup(masterSetup);

	}
	

	@Override
	public List<MasterSetup> getMasterSetupList(String masterType) {
		MasterSetup masterSetup = new MasterSetup();
		masterSetup.setMasterType(masterType);
		return masterSetupDAO.getMasterSetupList(masterSetup);
	}

}
