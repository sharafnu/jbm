package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.SystemProperty;

public class SystemPropertyExtractor implements
		ResultSetExtractor<SystemProperty> {

	@Override
	public SystemProperty extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		SystemProperty systemProperty = new SystemProperty();
		systemProperty.setId(rs.getLong("id"));
		systemProperty.setPropKey(rs.getString("prop_key"));
		systemProperty.setPropValue(rs.getString("prop_value"));
		return systemProperty;
	}

}
