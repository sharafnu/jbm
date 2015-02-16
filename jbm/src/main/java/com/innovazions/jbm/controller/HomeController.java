package com.innovazions.jbm.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.Roles;
import com.innovazions.jbm.entity.User;
import com.innovazions.jbm.service.impl.AccessManagerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends AbstractController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private AccessManagerService accessManagerService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Principal principal,
			HttpServletRequest request, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		if (principal == null) {
			return "login";
		}

		if (principal != null) {
			@SuppressWarnings("unchecked")
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
					.getContext().getAuthentication().getAuthorities();
			for (SimpleGrantedAuthority simpleGrantedAuthority : authorities) {
				System.out.println(simpleGrantedAuthority.getAuthority());
			}
			String loggedInUser = principal.getName();
			System.out.println("Logged in User : " + loggedInUser);
			User loggedInUserObj = accessManagerService
					.getUserDetailsByUserName(loggedInUser);
			request.getSession().setAttribute(JBMConstants.LOGGED_IN_USER_OBJ,
					loggedInUserObj);
		}
		Date date = CommonUtils.getCurrentDate("Asia/Dubai");
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = CommonUtils.getCurrentDate("Asia/Dubai");
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	public static void main(String[] args) {
		/*
		 * Roles[] x = Roles.values(); for (Roles roles : x) {
		 * System.out.println(roles.toString()); }
		 */
		System.out.println(Roles.getRoleName(1));
		System.out.println(Roles.getRoleName(2));
	}

}
