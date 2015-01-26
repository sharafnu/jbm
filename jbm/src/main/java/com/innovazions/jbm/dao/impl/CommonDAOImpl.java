package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.PropertiesUtil;
import com.innovazions.jbm.dao.CommonDAO;
import com.innovazions.jbm.entity.SystemProperty;
import com.innovazions.jbm.entity.jdbc.mapper.SystemPropertyRowMapper;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;

	@Override
	public List<SystemProperty> getAllSystemProperties() {
		List<SystemProperty> systemPropertyList = new ArrayList<SystemProperty>();

		String sql = "select id, prop_key, prop_value, prop_group, description from system_property";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		systemPropertyList = jdbcTemplate.query(sql,
				new SystemPropertyRowMapper());
		return systemPropertyList;
	}

	public String getSequenceCodeByType(String type, String prefixPropName) {
		String sequenceCode = "";
		sequence = new PostgreSQLSequenceMaxValueIncrementer(dataSource, type);
		String prefix = PropertiesUtil.getProperty(prefixPropName);
		if (!CommonUtils.isEmpty(prefix)) {
			sequenceCode = prefix + sequence.nextLongValue();
		} else {
			sequenceCode = sequence.nextLongValue() + "";
		}
		return sequenceCode;
	}
	
	@Override
	public long createSystemProperty(SystemProperty systemProperty) {
		String sql = "INSERT INTO system_property "
				+ "(prop_group, prop_key, prop_value, description) VALUES (?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { systemProperty.getPropGroup(),
				systemProperty.getPropKey(), systemProperty.getPropValue(),
				systemProperty.getDescription() });
		return 0;
	}

	@Override
	public void updateSystemProperty(SystemProperty systemProperty) {
		if (systemProperty != null && systemProperty.getId() > 0) {
			String sql = "UPDATE system_property SET prop_group=?, prop_key=?, prop_value=?, description=? where id=?";

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			jdbcTemplate.update(
					sql,
					new Object[] { systemProperty.getPropGroup(),
							systemProperty.getPropKey(),
							systemProperty.getPropValue(),
							systemProperty.getDescription(),
							systemProperty.getId() });
		}
	}

	@Override
	public void deleteSystemProperty(SystemProperty systemProperty) {
		if (systemProperty != null
				&& systemProperty.getId() > 0) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.execute("delete from system_property where id="
					+ systemProperty.getId());
		}
	}
}
