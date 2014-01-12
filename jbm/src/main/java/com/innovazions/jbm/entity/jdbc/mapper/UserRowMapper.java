package com.innovazions.jbm.entity.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innovazions.jbm.entity.User;
import com.innovazions.jbm.entity.jdbc.extractor.UserExtractor;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserExtractor userExtractor = new UserExtractor();
		return userExtractor.extractData(rs);
	}
}
