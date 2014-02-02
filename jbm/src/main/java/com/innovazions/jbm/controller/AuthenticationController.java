package com.innovazions.jbm.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AuthenticationController {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationController.class);

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			HttpServletRequest request, ModelMap model) {
		logger.debug("AuthenticationController > getLoginPage");

		if (error == true) {
			// Assign an error message
			model.put("error", "true");
			model.put("errorMessage", "Invalid username or password!");
		} else {
			model.put("error", "false");
			model.put("errorMessage", "");
		}

		// This will resolve to /WEB-INF/jsp/loginpage.jsp
		return "login";
	}

	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a
	 * regular user tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage(ModelMap model) {
		logger.debug("AuthenticationController > denied");
		model.put("error", "true");
		model.put("errorMessage", "You are un-authorized to access this page!");
		// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		logger.debug("AuthenticationController > logout");
		if(request.getSession() != null) {
			request.getSession().invalidate();
		}
		/*model.put("error", "true");
		model.put("errorMessage", "You are un-authorized to access this page!");
		*/// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "logout";
	}
	

}
