package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.City;

public class CityExtractor implements ResultSetExtractor<City> {

	@Override
	public City extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		City city = new City();
		city.setId(rs.getLong("CITY_ID"));
		city.setName(rs.getString("CITY_NAME"));
		return city;
	}

}
