package com.innovazions.jbm.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.AreaDAO;
import com.innovazions.jbm.entity.Area;

@Repository
public class AreaDAOImpl implements AreaDAO {

	@Autowired  
	private DataSource dataSource;  
	
	@Override
	public long createArea(Area area) {
		System.out.println("Inserting area..");
		String sql = "INSERT INTO area "
				+ "(name,city_id) VALUES (?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { area.getName(), area.getCity().getId() });
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
		// TODO Auto-generated method stub
		return null;
	}

}
