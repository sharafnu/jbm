package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.MasterSetup;

public class MasterSetupExtractor implements ResultSetExtractor<MasterSetup> {

	@Override
	public MasterSetup extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		MasterSetup masterSetup = new MasterSetup();
		masterSetup.setId(rs.getLong("ID"));
		masterSetup.setMasterType(rs.getString("MASTER_TYPE"));
		masterSetup.setCode(rs.getString("CODE"));
		masterSetup.setDescription(rs.getString("DESCRIPTION"));
		return masterSetup;
	}

}
