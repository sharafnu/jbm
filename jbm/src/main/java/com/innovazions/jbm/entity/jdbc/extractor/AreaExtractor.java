package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;

public class AreaExtractor implements ResultSetExtractor<Area> {

	@Override
	public Area extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Area area = new Area();
		area.setId(rs.getLong("AREA_ID"));

		City city = new City();
		city.setId(rs.getLong("CITY_ID"));
		city.setName(rs.getString("CITY_NAME"));
		area.setCity(city);

		area.setName(rs.getString("AREA_NAME"));
		return area;
	}

}
