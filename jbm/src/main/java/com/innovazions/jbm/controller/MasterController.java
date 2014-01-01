package com.innovazions.jbm.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.service.AreaService;
import com.innovazions.jbm.view.AreaView;
import com.innovazions.jbm.view.CityView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MasterController {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterController.class);
	
	@Autowired
	private AreaService areaService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/areaList", method = RequestMethod.GET)
	public String areaList(Locale locale, Model model) {
		logger.info("MasterController > areaList");
		return "areaList";
	}
	
	@RequestMapping(value = "/areaListJSON", method = RequestMethod.GET)
	public @ResponseBody List<AreaView> getAreaListJSON() {
		logger.info("MasterController > areaList");
		List<Area> areaList = areaService.getAreaList(null);
		return new Area().convertEntitiesToViews(areaList);
	}
	
	@RequestMapping(value = "/cityListJSON", method = RequestMethod.GET)
	public @ResponseBody List<CityView> getCityListJSON() {
		logger.info("MasterController > getCityListJSON");
		List<City> cityList = areaService.getAllCities();
		return new City().convertEntitiesToViews(cityList);
	}
	
	@RequestMapping(value = "/saveArea", method = RequestMethod.POST)
	public String saveArea(Locale locale, Model model) {
		logger.info("MasterController > saveArea");
		Area area = new Area();
		City city = new City();
		city.setId(1l);
		area.setCity(city);
		area.setName("DSO");		
		areaService.createArea(area);
		return "areaList";
	}
	
	@RequestMapping(value = "/staffDetails", method = RequestMethod.GET)
	public String staffList(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "staffDetails";
	}
	
}
