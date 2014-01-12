package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.User;

public class UserExtractor implements ResultSetExtractor<User> {

	@Override
	public User extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setFirstName(rs.getString("first_name"));
		user.setId(rs.getLong("id"));
		user.setLastName(rs.getString("last_name"));
		user.setPassword(rs.getString("password"));
		user.setRole(rs.getString("role"));
		user.setUsername(rs.getString("username"));
		return user;
	}

}
