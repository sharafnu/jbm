package com.innovazions.jbm.common;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovazions.jbm.entity.User;

public class JBMUIHelper implements JBMConstants {

	public static String getLoggedInUserName(HttpServletRequest request) {
		User loggedInUser = getLoggedInUserObj(request);
		if (loggedInUser != null && loggedInUser.getId() != null) {
			return loggedInUser.getUsername();
		}
		return "";
	}

	public static String getLoggedInUserFullName(HttpServletRequest request) {
		User loggedInUser = getLoggedInUserObj(request);
		if (loggedInUser != null && loggedInUser.getId() != null) {
			return loggedInUser.toString();
		}
		return "";
	}

	public static User getLoggedInUserObj(HttpServletRequest request) {
		User loggedInUser = null;
		loggedInUser = (User) request.getSession().getAttribute(
				LOGGED_IN_USER_OBJ);
		return loggedInUser;
	}

	public static boolean isAdminUser() {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		for (SimpleGrantedAuthority simpleGrantedAuthority : authorities) {
			if (simpleGrantedAuthority.getAuthority().equals(
					Roles.ROLE_ADMIN.toString())) {
				return true;
			}
		}
		return false;
	}
}
