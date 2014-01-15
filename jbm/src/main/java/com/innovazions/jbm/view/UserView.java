package com.innovazions.jbm.view;

import java.util.ArrayList;
import java.util.List;

import com.innovazions.jbm.common.Roles;
import com.innovazions.jbm.entity.User;
import com.innovazions.jbm.entity.UserRole;

public class UserView extends GenericView<UserView, User> {

	private Long id;

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private int userRole;

	private int adminRole;

	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public int getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(int adminRole) {
		this.adminRole = adminRole;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public User convertViewToEntity() {
		User user = new User();
		user.setEmail(this.getEmail());
		user.setEnabled(this.getEnabled());
		user.setFirstName(this.getFirstName());
		user.setId(this.getId());
		user.setLastModifiedDate(this.getLastModifiedDate());
		user.setLastModifiedUser(this.getLastModifiedUser());
		user.setLastName(this.getLastName());
		user.setPassword(this.getPassword());
		user.setUsername(this.getUsername());

		List<UserRole> userRoleList = new ArrayList<UserRole>();

		if (this.getUserRole() > 0) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(new Long(Roles.ROLE_USER.getRoleId()));
			userRoleList.add(userRole);
		}
		if (this.getAdminRole() > 0) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(new Long(Roles.ROLE_ADMIN.getRoleId()));
			userRoleList.add(userRole);
		}
		user.setRoles(userRoleList);
		return user;
	}

	@Override
	public List<User> convertViewsToEntities(List<UserView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}
}
