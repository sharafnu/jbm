package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.jdbc.extractor.AreaExtractor;

public class AreaRowMapper implements RowMapper<Area> {

	@Override
	public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
		AreaExtractor areaExtractor = new AreaExtractor();
		return areaExtractor.extractData(rs);
	}

}
