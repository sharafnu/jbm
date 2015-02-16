package com.innovazions.jbm.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.JBMUIHelper;
import com.innovazions.jbm.common.PropertiesUtil;
import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.entity.MasterSetup;
import com.innovazions.jbm.entity.SystemProperty;
import com.innovazions.jbm.entity.User;
import com.innovazions.jbm.service.AreaService;
import com.innovazions.jbm.service.CommonService;
import com.innovazions.jbm.service.EmployeeService;
import com.innovazions.jbm.service.MasterSetupService;
import com.innovazions.jbm.service.impl.AccessManagerService;
import com.innovazions.jbm.view.ActionStatus;
import com.innovazions.jbm.view.AreaView;
import com.innovazions.jbm.view.CityView;
import com.innovazions.jbm.view.EmployeeView;
import com.innovazions.jbm.view.MasterSetupView;
import com.innovazions.jbm.view.SystemPropertyView;
import com.innovazions.jbm.view.UserView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MasterController extends AbstractController {

	private static final Logger logger = LoggerFactory
			.getLogger(MasterController.class);

	@Autowired
	private AreaService areaService;

	@Autowired
	private MasterSetupService masterSetupService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AccessManagerService userService;

	@Autowired
	private ApplicationContext applicationContext;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/areaList", method = RequestMethod.GET)
	public String areaList(Locale locale, Model model,
			HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "home";
		}
		logger.info("MasterController > areaList");
		return "areaList";
	}

	@RequestMapping(value = "/areaListJSON", method = RequestMethod.GET)
	public @ResponseBody List<AreaView> getAreaListJSON() {
		logger.info("MasterController > areaList");
		List<Area> areaList = areaService.getAreaList(null);
		return new Area().convertEntitiesToViews(areaList);
	}

	/**
	 * 
	 * Actions for MasterSetup Starts Here >>
	 */
	@RequestMapping(value = "/masterList", method = RequestMethod.GET)
	public String masterList(Locale locale, Model model,
			HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "home";
		}
		logger.info("MasterController > masterList");
		return "masterSetupList";
	}

	@RequestMapping(value = "/masterListJSON/{masterType}", method = RequestMethod.GET)
	public @ResponseBody List<MasterSetupView> getMasterListJSON(
			@PathVariable String masterType) {
		logger.info("MasterController > masterListJSON");
		List<MasterSetup> masterSetupList = masterSetupService
				.getMasterSetupList(masterType);
		return new MasterSetup().convertEntitiesToViews(masterSetupList);
	}

	@RequestMapping(value = "/saveMasterSetup", method = RequestMethod.POST)
	public @ResponseBody ActionStatus saveMasterSetup(
			@ModelAttribute("masterSetupView") MasterSetupView masterSetupView,
			BindingResult result, HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return CommonUtils.getUnAuthorizedAccessActionStatus();
		}
		System.out.println("Master Name:" + masterSetupView.getCode()
				+ " Desc:" + masterSetupView.getDescription());
		MasterSetup masterSetup = masterSetupView.convertViewToEntity();
		if (masterSetup != null && masterSetup.getId() != null
				&& masterSetup.getId() > 0) {
			masterSetupService.updateMasterSetup(masterSetup);
			return CommonUtils.getDataUpdateSuccessActionStatus();
		} else {
			masterSetupService.createMasterSetup(masterSetup);
			return CommonUtils.getDataSaveSuccessActionStatus();
		}
	}

	@RequestMapping(value = "/deleteMasterSetup", method = RequestMethod.POST)
	public @ResponseBody ActionStatus deleteMasterSetup(
			@ModelAttribute("masterSetupView") MasterSetupView masterSetupView,
			BindingResult result, HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return CommonUtils.getUnAuthorizedAccessActionStatus();
		}
		MasterSetup masterSetup = masterSetupView.convertViewToEntity();

		ActionStatus message = CommonUtils.getDataDeleteSuccessActionStatus();
		try {
			masterSetupService.deleteMasterSetup(masterSetup);
		} catch (Exception e) {
			e.printStackTrace();
			message = CommonUtils
					.getErrorActionStatus("Unable to delete. Reference records exists");
		}

		return message;
	}

	/**
	 * 
	 * Actions for MasterSetup Ends Here >>
	 */

	/**
	 * 
	 * Actions for SystemProperty Starts Here >>
	 */
	@RequestMapping(value = "/systemProperyList", method = RequestMethod.GET)
	public String systemProperyList(Locale locale, Model model,
			HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "home";
		}
		logger.info("MasterController > propertyList");
		return "systemProperyList";
	}

	@RequestMapping(value = "/systemProperyListJSON", method = RequestMethod.GET)
	public @ResponseBody List<SystemPropertyView> systemProperyListJSON() {
		logger.info("MasterController > systemProperyListJSON");
		List<SystemProperty> systemPropertyList = commonService
				.getAllSystemProperties();
		return new SystemProperty().convertEntitiesToViews(systemPropertyList);
	}

	@RequestMapping(value = "/saveSystemProperty", method = RequestMethod.POST)
	public @ResponseBody ActionStatus saveSystemProperty(
			@ModelAttribute("systemPropertyView") SystemPropertyView systemPropertyView,
			BindingResult result, HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return CommonUtils.getUnAuthorizedAccessActionStatus();
		}
		System.out.println("System Property Name:"
				+ systemPropertyView.getPropKey() + " Desc:"
				+ systemPropertyView.getDescription());
		SystemProperty systemProperty = systemPropertyView
				.convertViewToEntity();
		if (systemProperty != null && systemProperty.getId() > 0) {
			commonService.updateSystemProperty(systemProperty);
			// applicationContext starts life as ClassPathXmlApplicationContext("applicationContext.xml");
		    ((XmlWebApplicationContext) applicationContext).refresh(); 
			return CommonUtils.getDataUpdateSuccessActionStatus();
		} else {
			commonService.createSystemProperty(systemProperty);
			// applicationContext starts life as ClassPathXmlApplicationContext("applicationContext.xml");
		    ((XmlWebApplicationContext) applicationContext).refresh(); 

				return CommonUtils.getDataSaveSuccessActionStatus();
		}
	}

	@RequestMapping(value = "/deleteSystemProperty", method = RequestMethod.POST)
	public @ResponseBody ActionStatus deleteSystemProperty(
			@ModelAttribute("systemPropertyView") SystemPropertyView systemPropertyView,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Deleting SystemProperty"+systemPropertyView.getId());
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return CommonUtils.getUnAuthorizedAccessActionStatus();
		}
		SystemProperty systemProperty = systemPropertyView
				.convertViewToEntity();

		ActionStatus message = CommonUtils.getDataDeleteSuccessActionStatus();
		try {
			commonService.deleteSystemProperty(systemProperty);
			((XmlWebApplicationContext) applicationContext).refresh(); 
			return CommonUtils.getDataUpdateSuccessActionStatus();
		} catch (Exception e) {
			e.printStackTrace();
			message = CommonUtils
					.getErrorActionStatus("Unable to delete. Reference records exists");
		}

		return message;
	}

	/**
	 * 
	 * Actions for System Property Ends Here >>
	 */

	@RequestMapping(value = "/saveArea", method = RequestMethod.POST)
	public @ResponseBody String saveArea(
			@ModelAttribute("areaView") AreaView areaView,
			BindingResult result, HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "home";
		}
		System.out.println("Area Name:" + areaView.getAreaName() + " City Id:"
				+ areaView.getCityId());
		Area area = areaView.convertViewToEntity();
		areaService.createArea(area);
		return "Success";
	}

	@RequestMapping(value = "/cityListJSON", method = RequestMethod.GET)
	public @ResponseBody List<CityView> getCityListJSON() {
		logger.info("MasterController > getCityListJSON");
		List<City> cityList = areaService.getAllCities();
		return new City().convertEntitiesToViews(cityList);
	}

	@RequestMapping(value = "/staffDetails", method = RequestMethod.GET)
	public String staffDetails(Locale locale, Model model,
			HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "home";
		}
		logger.info("Master Controller >> staffDetails");
		return "staffList";
	}

	@RequestMapping(value = "/staffListJSON", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeView> staffListJSON() {
		logger.info("MasterController > staffListJSON");
		List<Employee> employeeList = employeeService.getEmployeeList(null);
		return new Employee().convertEntitiesToViews(employeeList);
	}

	@RequestMapping(value = "/activeStaffListJSON", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeView> activeStaffListJSON() {
		logger.info("MasterController > staffListJSON");
		Employee employee = new Employee();
		employee.setEmployeeStatus(JBMConstants.EMPLOYEE_STATUS_ACTIVE);
		List<Employee> employeeList = employeeService.getEmployeeList(employee);
		return new Employee().convertEntitiesToViews(employeeList);
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	public @ResponseBody ActionStatus deleteEmployee(
			@ModelAttribute("employeeView") EmployeeView employeeView,
			BindingResult result, HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return CommonUtils.getUnAuthorizedAccessActionStatus();
		}
		Employee employee = employeeView.convertViewToEntity();

		ActionStatus message = CommonUtils.getDataDeleteSuccessActionStatus();
		try {
			employeeService.deleteEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
			message = CommonUtils
					.getErrorActionStatus("Unable to delete. Reference records exists");
		}

		return message;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public @ResponseBody ActionStatus saveEmployee(
			@ModelAttribute("employeeView") EmployeeView employeeView,
			BindingResult result, HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return CommonUtils.getUnAuthorizedAccessActionStatus();
		}
		System.out.println("Employee Name:" + employeeView.getFirstName()
				+ " Join Date:" + employeeView.getJoinDate());
		Employee employee = employeeView.convertViewToEntity();
		if (CommonUtils.isEmpty(employee.getEmployeeCode())) {
			String empCode = commonService.getSequenceCodeByType(
					JBMConstants.SEQ_EMPLOYEE_CODE,
					JBMConstants.PROP_PREFIX_EMPLOYEE_CODE);
			employee.setEmployeeCode(empCode);
			Long employeeId = employeeService.createEmployee(employee);
			return CommonUtils.getDataSaveSuccessActionStatus(employeeId,
					empCode);
		} else {
			employeeService.updateEmployee(employee);
			return CommonUtils.getDataUpdateSuccessActionStatus();
		}

	}

	@RequestMapping(value = "/userDetails", method = RequestMethod.GET)
	public String userDetails(Locale locale, Model model,
			HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "home";
		}
		logger.info("Master Controller >> userDetails");
		return "userList";
	}

	@RequestMapping(value = "/userListJSON", method = RequestMethod.GET)
	public @ResponseBody List<UserView> userListJSON(Model model,
			HttpServletRequest request) {
		logger.info("MasterController > userListJSON");
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return null;
		}
		List<User> userList = userService.getAllUserList();
		return new User().convertEntitiesToViews(userList);
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public @ResponseBody String saveUser(
			@ModelAttribute("userView") UserView userView,
			BindingResult result, HttpServletRequest request) {
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "home";
		}
		System.out.println("User Name:" + userView.getFirstName() + " User Id:"
				+ userView.getUsername());
		User user = userView.convertViewToEntity();
		user.setLastModifiedDate(CommonUtils.getCurrentDate("Asia/Dubai"));
		user.setLastModifiedUser("SYSTEM");
		userService.createUser(user);
		return "Success";
	}

	@RequestMapping(value = "/timeComboList", method = RequestMethod.GET)
	public @ResponseBody List<String> timeComboList(Model model,
			HttpServletRequest request) {
		logger.info("MasterController > timeComboList");
		return JBMUIHelper.timeComboList();
	}
}
