package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.UserRole;

public class UserRoleExtractor implements ResultSetExtractor<UserRole> {

	@Override
	public UserRole extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		UserRole userRole = new UserRole();
		userRole.setId(rs.getLong("id"));
		userRole.setRoleId(rs.getLong("role_id"));
		userRole.setUserId(rs.getLong("user_id"));
		return userRole;
	}

}
