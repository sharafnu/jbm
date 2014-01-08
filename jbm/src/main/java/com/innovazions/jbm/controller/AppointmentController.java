package com.innovazions.jbm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.AppointmentService;
import com.innovazions.jbm.service.EmailNotificationService;
import com.innovazions.jbm.service.SMSNotificationService;
import com.innovazions.jbm.view.AppointmentView;
import com.innovazions.jbm.view.StaffAppoinmentInfoView;

/**
 * Handles requests for the customer related actions.
 */
@Controller
public class AppointmentController {

	private static final Logger logger = LoggerFactory
			.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private EmailNotificationService emailNotificationService;

	@Autowired
	private SMSNotificationService smsNotificationService;

	/*
	 * Customer Contract actions starts
	 */

	@RequestMapping(value = "/customerAppointmentList")
	public String customerAppointmentList(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model) {
		logger.info("AppoinmentController > customerAppointmentList");

		List<Appointment> appointmentList = appointmentService
				.getAppointmentListByFilter(appointmentView
						.convertViewToEntity());
		List<AppointmentView> appointmentViewList = new Appointment()
				.convertEntitiesToViews(appointmentList);
		ObjectMapper objectMapper = new ObjectMapper();
		String appointmentViewListJSON = "";
		try {
			appointmentViewListJSON = objectMapper
					.writeValueAsString(appointmentViewList);
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

	@RequestMapping(value = "/customerAppoinmentListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<AppointmentView> customerAppoinmentListJSON() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getAppointmentListByFilter(null);
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerApointmentAdd", method = RequestMethod.GET)
	public String customerApointmentAdd(Locale locale, Model model) {
		logger.info("AppoinmentController > customerApointmentAdd");

		return "customerApointmentAdd";
	}

	@RequestMapping(value = "/saveCustomerAppoinment", method = RequestMethod.POST)
	public @ResponseBody
	String saveCustomerAppoinment(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result) {
		System.out.println("Customer Id:"
				+ appointmentView.getCustomerId() + " Appointment No:"
				+ appointmentView.getAppointmentNo());
		appointmentView.setLastModifiedDate(new Date());
		appointmentView.setLastModifiedUser("SYSTEM");
		Appointment appointment = appointmentView.convertViewToEntity();
		/*appointmentService.createAppointment(appointment);
		// send mail
		emailNotificationService
				.sendAppoinmentCreationEmailNotification(appointment);
		smsNotificationService
				.sendAppoinmentCreationSMSNotification(appointment);*/
		return "Success";
	}

	@RequestMapping(value = "/customerAppointmentDetails")
	public String customerAppointmentDetails(Model model) {
		logger.info("AppoinmentController > customerAppointmentDetails");
		return "customerAppointmentDetails";
	}

	@RequestMapping(value = "/getCustomerAppointmentDetails/{appointmentId}", method = RequestMethod.GET)
	public @ResponseBody
	AppointmentView getCustomerAppointmentDetails(
			@PathVariable Long appointmentId) {
		logger.info("AppoinmentController > getCustomerAppointmentDetails :"
				+ appointmentId);
		Appointment appointment = appointmentService
				.getAppoinmentDetailsByAppoinmentId(appointmentId);
		return appointment.convertEntityToView();
	}
	
	@RequestMapping(value = "/staffAppoinmentCountListJSON/{appointmentId}", method = RequestMethod.GET)
	public @ResponseBody
	List<StaffAppoinmentInfoView> staffAppoinmentCountListJSON(
			@PathVariable Long appointmentId) {
		logger.info("AppoinmentController > getCustomerAppointmentDetails :"
				+ appointmentId);
		List<StaffAppoinmentInfoView> appoinmentInfoList = new ArrayList<StaffAppoinmentInfoView>();
		StaffAppoinmentInfoView appoinmentInfoView1 = new StaffAppoinmentInfoView(1l, "Sharafudeen Aboobacker", 10d);
		StaffAppoinmentInfoView appoinmentInfoView2 = new StaffAppoinmentInfoView(2l, "Salman Aboobacker", 0d);
		StaffAppoinmentInfoView appoinmentInfoView3 = new StaffAppoinmentInfoView(3l, "Sainaba Aboobacker", 20d);
		StaffAppoinmentInfoView appoinmentInfoView4 = new StaffAppoinmentInfoView(4l, "Mariyam M V", 2d);
		StaffAppoinmentInfoView appoinmentInfoView5 = new StaffAppoinmentInfoView(5l, "Hana Fathima", 1d);
		StaffAppoinmentInfoView appoinmentInfoView6 = new StaffAppoinmentInfoView(6l, "Nadeer Ali", 7d);
		StaffAppoinmentInfoView appoinmentInfoView7 = new StaffAppoinmentInfoView(7l, "Mujeeb Khan", 22d);
		StaffAppoinmentInfoView appoinmentInfoView8 = new StaffAppoinmentInfoView(8l, "Adil Borkar", 34d);
		StaffAppoinmentInfoView appoinmentInfoView9 = new StaffAppoinmentInfoView(9l, "Ayisha M V", 15d);
		StaffAppoinmentInfoView appoinmentInfoView10 = new StaffAppoinmentInfoView(10l, "Ishan Muhammed", 20d);
		
		StaffAppoinmentInfoView appoinmentInfoView11 = new StaffAppoinmentInfoView(11l, "Tapas Kumar", 20d);
		StaffAppoinmentInfoView appoinmentInfoView12 = new StaffAppoinmentInfoView(12l, "Ritesh Kumar", 20d);
		StaffAppoinmentInfoView appoinmentInfoView13 = new StaffAppoinmentInfoView(13l, "Sinu Sarun Sam", 20d);
		StaffAppoinmentInfoView appoinmentInfoView14 = new StaffAppoinmentInfoView(14l, "Mallika Shetty", 20d);
		StaffAppoinmentInfoView appoinmentInfoView15 = new StaffAppoinmentInfoView(15l, "Biju Lazer", 20d);
		StaffAppoinmentInfoView appoinmentInfoView16 = new StaffAppoinmentInfoView(16l, "Sinique Muhammed", 20d);
		StaffAppoinmentInfoView appoinmentInfoView17 = new StaffAppoinmentInfoView(17l, "Mohammed Rashid", 20d);
		StaffAppoinmentInfoView appoinmentInfoView18 = new StaffAppoinmentInfoView(18l, "Saju Kabir", 20d);
		
		appoinmentInfoList.add(appoinmentInfoView1);
		appoinmentInfoList.add(appoinmentInfoView2);
		appoinmentInfoList.add(appoinmentInfoView3);
		appoinmentInfoList.add(appoinmentInfoView4);
		appoinmentInfoList.add(appoinmentInfoView5);
		appoinmentInfoList.add(appoinmentInfoView6);
		appoinmentInfoList.add(appoinmentInfoView7);
		appoinmentInfoList.add(appoinmentInfoView8);
		appoinmentInfoList.add(appoinmentInfoView9);
		appoinmentInfoList.add(appoinmentInfoView10);
		
		
		appoinmentInfoList.add(appoinmentInfoView11);
		appoinmentInfoList.add(appoinmentInfoView12);
		appoinmentInfoList.add(appoinmentInfoView13);
		appoinmentInfoList.add(appoinmentInfoView14);
		appoinmentInfoList.add(appoinmentInfoView15);
		appoinmentInfoList.add(appoinmentInfoView16);
		appoinmentInfoList.add(appoinmentInfoView17);
		appoinmentInfoList.add(appoinmentInfoView18);
		
		
		return appoinmentInfoList;
	}
}
