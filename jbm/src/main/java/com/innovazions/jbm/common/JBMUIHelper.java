package com.innovazions.jbm.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovazions.jbm.entity.User;

public class JBMUIHelper implements JBMConstants {

	public static String getLoggedInUserName(HttpServletRequest request,
			HttpServletResponse response) {
		User loggedInUser = getLoggedInUserObj(request, response);
		if (loggedInUser != null && loggedInUser.getId() != null) {
			return loggedInUser.getUsername();
		}
		return "";
	}

	public static String getLoggedInUserFullName(HttpServletRequest request,
			HttpServletResponse response) {
		User loggedInUser = getLoggedInUserObj(request, response);
		if (loggedInUser != null && loggedInUser.getId() != null) {
			return loggedInUser.toString();
		}
		return "";
	}

	public static User getLoggedInUserObj(HttpServletRequest request, HttpServletResponse response) {
		User loggedInUser = null;
		loggedInUser = (User) request.getSession().getAttribute(
				LOGGED_IN_USER_OBJ);
		if (loggedInUser == null) {
			try {
				response.sendRedirect("/logout.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	public static void main(String[] args) {
		timeComboList();
	}

	public static List<String> timeComboList() {
		List<String> hourList = new ArrayList<String>();
		int morningstart = 9;
		int morningend = 12;
		for (int i = morningstart; i < morningend; i++) {
			String anHour = String.format("%02d:00", i);
			hourList.add(anHour + " am");
			anHour = String.format("%02d:30", i);
			hourList.add(anHour + " am");
		}

		String anHour = String.format("%02d:00", 12);
		hourList.add(anHour + " pm");
		int afternoonstart = 1;
		int afternoonend = 9;
		for (int i = afternoonstart; i < afternoonend; i++) {
			anHour = String.format("%02d:00", i);
			hourList.add(anHour + " pm");
			anHour = String.format("%02d:30", i);
			hourList.add(anHour + " pm");
		}

		return hourList;
	}

}
