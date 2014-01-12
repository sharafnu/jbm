package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.UserRole;
import com.innovazions.jbm.entity.jdbc.extractor.UserRoleExtractor;

public class UserRoleRowMapper implements RowMapper<UserRole> {

	@Override
	public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserRoleExtractor userRoleExtractor = new UserRoleExtractor();
		return userRoleExtractor.extractData(rs);
	}
}
