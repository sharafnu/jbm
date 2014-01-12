package com.innovazions.jbm.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.UserDAO;
import com.innovazions.jbm.entity.UserRole;
import com.innovazions.jbm.entity.jdbc.mapper.UserRoleRowMapper;
import com.innovazions.jbm.entity.jdbc.mapper.UserRowMapper;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public com.innovazions.jbm.entity.User getUserDetailsByUserName(
			String userName) {
		String sql = "SELECT id, username, email, first_name, last_name, password, role, enabled FROM sec_user where username=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<com.innovazions.jbm.entity.User> userList = jdbcTemplate.query(
				sql, new Object[] { userName }, new UserRowMapper());
		if (userList.isEmpty()) {
			return null;
		} else {
			return userList.get(0);
		}
	}

	@Override
	public List<UserRole> getUserRoles(Long userId) {
		String sql = "SELECT id, user_id, role_id FROM sec_user_role where user_id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<UserRole> userRoleList = jdbcTemplate.query(sql,
				new Object[] { userId }, new UserRoleRowMapper());
		return userRoleList;
	}

}
