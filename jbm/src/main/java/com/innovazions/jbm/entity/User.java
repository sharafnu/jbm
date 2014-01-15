package com.innovazions.jbm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.innovazions.jbm.common.Roles;
import com.innovazions.jbm.view.UserView;

public class User extends CoreEntity<User, UserView> implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String role;

	private Boolean enabled;

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	private List<UserRole> roles;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return ((getFirstName() != null ? getFirstName() : "") + " " + (getLastName() != null ? getLastName()
				: ""));
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(getRole()));
		return list;
	}

	@Override
	public UserView convertEntityToView() {
		UserView userView = new UserView();
		userView.setEmail(this.getEmail());
		userView.setEnabled(this.getEnabled());
		userView.setFirstName(this.getFirstName());
		userView.setLastName(this.getLastName());
		userView.setId(this.getId());
		userView.setLastModifiedDate(this.getLastModifiedDate());
		userView.setLastModifiedUser(this.getLastModifiedUser());
		userView.setLastName(this.getLastName());
		//Mask the password for view
		userView.setPassword("xxxx");
		userView.setUsername(this.getUsername());
		if (this.getRoles() != null) {

			for (UserRole aRole : this.getRoles()) {
				if (aRole.getRoleId() == Roles.ROLE_ADMIN.getRoleId()) {
					userView.setAdminRole(1);
				} else if (aRole.getRoleId() == Roles.ROLE_USER.getRoleId()) {
					userView.setUserRole(1);
				}
			}
		}
		return userView;
	}

	@Override
	public List<UserView> convertEntitiesToViews(List<User> entityList) {
		List<UserView> userViewList = new ArrayList<UserView>();
		for (User user : entityList) {
			userViewList.add(user.convertEntityToView());
		}
		return userViewList;
	}
}