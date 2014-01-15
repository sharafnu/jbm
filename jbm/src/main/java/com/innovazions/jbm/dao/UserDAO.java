package com.innovazions.jbm.dao;

import java.util.List;

import com.innovazions.jbm.entity.User;
import com.innovazions.jbm.entity.UserRole;


public interface UserDAO {

	public com.innovazions.jbm.entity.User getUserDetailsByUserName(
			String userName);
	
	public List<UserRole> getUserRoles(Long userId);

	public List<User> getAllUserList();

	public Long createUser(User user);
	
	public void createUserRole(UserRole userRole);
}
