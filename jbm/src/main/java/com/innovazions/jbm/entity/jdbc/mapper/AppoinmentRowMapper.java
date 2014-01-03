package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.jdbc.extractor.AppoinmentExtractor;

public class AppoinmentRowMapper implements RowMapper<Appointment> {

	@Override
	public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppoinmentExtractor appoinmentExtractor = new AppoinmentExtractor();
		return appoinmentExtractor.extractData(rs);
	}
}
