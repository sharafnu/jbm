package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.SystemProperty;
import com.innovazions.jbm.entity.jdbc.extractor.SystemPropertyExtractor;

public class SystemPropertyRowMapper implements RowMapper<SystemProperty> {

	@Override
	public SystemProperty mapRow(ResultSet rs, int rowNum) throws SQLException {
		SystemPropertyExtractor propertyExtractor = new SystemPropertyExtractor();
		return propertyExtractor.extractData(rs);
	}

}
