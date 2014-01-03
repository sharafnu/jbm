package com.innovazions.jbm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.AppoinmentService;
import com.innovazions.jbm.view.AppointmentView;

/**
 * Handles requests for the customer related actions.
 */
@Controller
public class AppoinmentController {

	private static final Logger logger = LoggerFactory
			.getLogger(AppoinmentController.class);

	@Autowired
	private AppoinmentService appoinmentService;

	/*
	 * Customer Contract actions starts
	 */

	@RequestMapping(value = "/customerAppointmentList")
	public String customerAppointmentList(Model model) {
		logger.info("AppoinmentController > customerAppointmentList");

		List<Appointment> appointmentList = appoinmentService
				.getAppointmentListByFilter(null);
		List<AppointmentView> appointmentViewList = new Appointment()
				.convertEntitiesToViews(appointmentList);
		ObjectMapper objectMapper = new ObjectMapper();
		String appointmentViewListJSON = "";
		try {
			appointmentViewListJSON = objectMapper.writeValueAsString(appointmentViewList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("appoinmentListJSON", appointmentViewListJSON);
		return "customerAppointmentList";
	}

	@RequestMapping(value = "/getCustomerAppoinmentListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<AppointmentView> getCustomerAppoinmentListJSON() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appoinmentService
				.getAppointmentListByFilter(null);
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/saveCustomerAppoinment", method = RequestMethod.POST)
	public @ResponseBody
	String saveCustomerAppoinment(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result) {
		System.out.println("Customer Contract:"
				+ appointmentView.getCustomerId() + " Appointment No:"
				+ appointmentView.getAppointmentNo());
		appointmentView.setLastModifiedDate(new Date());
		appointmentView.setLastModifiedUser("SYSTEM");
		Appointment appointment = appointmentView.convertViewToEntity();
		appoinmentService.createAppointment(appointment);
		return "Success";
	}
}
