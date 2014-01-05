package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.jdbc.extractor.AppointmentExtractor;

public class AppointmentRowMapper implements RowMapper<Appointment> {

	@Override
	public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppointmentExtractor appoinmentExtractor = new AppointmentExtractor();
		return appoinmentExtractor.extractData(rs);
	}
}
