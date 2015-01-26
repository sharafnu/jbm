package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.jdbc.extractor.AppointmentDetailExtractor;

public class AppointmentDetailRowMapper implements RowMapper<Appointment> {

	@Override
	public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppointmentDetailExtractor appointmentDetailExtractor = new AppointmentDetailExtractor();
		return appointmentDetailExtractor.extractData(rs);
	}
}
