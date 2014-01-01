package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.jdbc.extractor.CityExtractor;

public class CityRowMapper implements RowMapper<City> {

	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		CityExtractor cityExtractor = new CityExtractor();
		return cityExtractor.extractData(rs);
	}

}
