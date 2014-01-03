package com.innovazions.jbm.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.service.AreaService;
import com.innovazions.jbm.service.EmployeeService;
import com.innovazions.jbm.view.AreaView;
import com.innovazions.jbm.view.CityView;
import com.innovazions.jbm.view.EmployeeView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MasterController {

	private static final Logger logger = LoggerFactory
			.getLogger(MasterController.class);

	@Autowired
	private AreaService areaService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/areaList", method = RequestMethod.GET)
	public String areaList(Locale locale, Model model) {
		logger.info("MasterController > areaList");
		return "areaList";
	}

	@RequestMapping(value = "/areaListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<AreaView> getAreaListJSON() {
		logger.info("MasterController > areaList");
		List<Area> areaList = areaService.getAreaList(null);
		return new Area().convertEntitiesToViews(areaList);
	}

	@RequestMapping(value = "/saveArea", method = RequestMethod.POST)
	public @ResponseBody
	String saveArea(@ModelAttribute("areaView") AreaView areaView,
			BindingResult result) {
		System.out.println("Area Name:" + areaView.getAreaName() + " City Id:"
				+ areaView.getCityId());
		Area area = areaView.convertViewToEntity();
		areaService.createArea(area);
		return "Success";
	}

	@RequestMapping(value = "/cityListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<CityView> getCityListJSON() {
		logger.info("MasterController > getCityListJSON");
		List<City> cityList = areaService.getAllCities();
		return new City().convertEntitiesToViews(cityList);
	}

	@RequestMapping(value = "/staffDetails", method = RequestMethod.GET)
	public String staffDetails(Locale locale, Model model) {
		logger.info("Master Controller >> staffDetails");
		return "staffList";
	}

	@RequestMapping(value = "/staffListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<EmployeeView> staffListJSON() {
		logger.info("MasterController > staffListJSON");
		List<Employee> employeeList = employeeService.getEmployeeList(null);
		return new Employee().convertEntitiesToViews(employeeList);
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public @ResponseBody
	String saveEmployee(
			@ModelAttribute("employeeView") EmployeeView employeeView,
			BindingResult result) {
		System.out.println("Employee Name:" + employeeView.getFirstName()
				+ " Join Date:" + employeeView.getJoinDate());
		Employee employee = employeeView.convertViewToEntity();
		employeeService.createEmployee(employee);
		return "Success";
	}
}
