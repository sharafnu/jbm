package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.CommonDAO;
import com.innovazions.jbm.entity.SystemProperty;
import com.innovazions.jbm.entity.jdbc.mapper.SystemPropertyRowMapper;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<SystemProperty> getAllSystemProperties() {
		List<SystemProperty> systemPropertyList = new ArrayList<SystemProperty>();

		String sql = "select id, prop_key, prop_value from system_property";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		systemPropertyList = jdbcTemplate.query(sql,
				new SystemPropertyRowMapper());
		return systemPropertyList;
	}

}
