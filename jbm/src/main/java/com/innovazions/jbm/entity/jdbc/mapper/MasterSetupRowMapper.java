package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.MasterSetup;
import com.innovazions.jbm.entity.jdbc.extractor.MasterSetupExtractor;

public class MasterSetupRowMapper implements RowMapper<MasterSetup> {

	@Override
	public MasterSetup mapRow(ResultSet rs, int rowNum) throws SQLException {
		MasterSetupExtractor masterSetupExtractor = new MasterSetupExtractor();
		return masterSetupExtractor	.extractData(rs);
	}

}
