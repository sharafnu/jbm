package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.AreaDAO;
import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.jdbc.mapper.AreaRowMapper;
import com.innovazions.jbm.entity.jdbc.mapper.CityRowMapper;

@Repository
public class AreaDAOImpl implements AreaDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public long createArea(Area area) {
		System.out.println("Inserting area..");
		String sql = "INSERT INTO area " + "(name,city_id) VALUES (?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { area.getName(),
				area.getCity().getId() });
		return 0;
	}

	@Override
	public long updateArea(Area area) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteArea(Area area) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Area> getAreaList(Area area) {
		List<Area> areaList = new ArrayList<Area>();

		String sql = "select a.id as area_id, a.name as area_name, "
				+ "a.city_id as city_id, c.name as city_name  from area a "
				+ "inner join city c on a.city_id=c.id";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		areaList = jdbcTemplate.query(sql, new AreaRowMapper());
		return areaList;
	}

	@Override
	public List<City> getAllCities() {
		List<City> cityList = new ArrayList<City>();

		String sql = "select c.id as city_id, c.name as city_name from city c order by c.name";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		cityList = jdbcTemplate.query(sql, new CityRowMapper());
		return cityList;
	}

}
