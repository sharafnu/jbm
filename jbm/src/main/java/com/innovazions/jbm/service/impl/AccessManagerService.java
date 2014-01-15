package com.innovazions.jbm.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innovazions.jbm.common.Roles;
import com.innovazions.jbm.dao.UserDAO;
import com.innovazions.jbm.entity.User;
import com.innovazions.jbm.entity.UserRole;

/**
 * An implementation of Spring Security's UserDetailsService.
 */
@Service("accessManagerService")
public class AccessManagerService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	private PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {

		/*
		 * System.out.println("Doing test connection"); try { User user =
		 * userDAO.findByEmail(email); } catch (Exception e) { // TODO: handle
		 * exception System.out.println("Test connection failed");
		 * e.getMessage(); }
		 */
		if ((userName == null) || userName.trim().isEmpty()) {
			throw new UsernameNotFoundException("Email is null or empty");
		}

		User user = userDAO.getUserDetailsByUserName(userName);

		if (user == null) {
			String errorMsg = "User with email: " + userName
					+ " could not be found";
			throw new UsernameNotFoundException(errorMsg);
		}

		Collection<GrantedAuthority> grantedAuthorities = user.getAuthorities();
		String password = user.getPassword();
		boolean enabled = user.isEnabled();
		boolean userNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean userNonLocked = true;

		return new org.springframework.security.core.userdetails.User(userName,
				password, enabled, userNonExpired, credentialsNonExpired,
				userNonLocked, getAuthorities(user.getId()));
	}

	public Collection<GrantedAuthority> getAuthorities(Long userId) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// All users are granted with ROLE_USER access // Therefore this user
		// gets a ROLE_USER by default
		List<UserRole> userRoleList = userDAO.getUserRoles(userId);
		for (UserRole userRole : userRoleList) {
			SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
					Roles.getRoleName(userRole.getRoleId().intValue()));
			authList.add(grantedAuthority);
		}
		return authList;
	}

	public User getUserDetailsByUserName(String userName) {
		return userDAO.getUserDetailsByUserName(userName);
	}

	public List<User> getAllUserList() {
		List<User> userList = new ArrayList<User>();
		for (User user : userDAO.getAllUserList()) {
			user.setRoles(userDAO.getUserRoles(user.getId()));
			userList.add(user);
		}
		return userList;

	}

	public void createUser(User user) {
		String encodePwd = passwordEncoder.encodePassword(user.getPassword(),
				"jbm");
		user.setPassword(encodePwd);
		Long userId = userDAO.createUser(user);
		List<UserRole> userRoles = user.getRoles();
		for (UserRole userRole : userRoles) {
			userRole.setUserId(userId);
			userDAO.createUserRole(userRole);
		}
	}
	
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder1 = new Md5PasswordEncoder();
		String encodePwd = passwordEncoder1.encodePassword("admin123",
				"jbm");
		System.out.println(encodePwd);
		
		/*String encodedPwd = "1ee8ef9e4cc274d1f696c562c2d5dced";
		boolean x = passwordEncoder1.isPasswordValid(encodedPwd, "pass@123", "default");
		System.out.println(x);*/
	}
}