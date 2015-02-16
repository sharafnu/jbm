package com.innovazions.jbm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.innovazions.jbm.common.ActionMessages;
import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.JBMUIHelper;
import com.innovazions.jbm.common.PropertiesUtil;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.service.AppointmentService;
import com.innovazions.jbm.service.CommonService;
import com.innovazions.jbm.service.CustomerService;
import com.innovazions.jbm.service.EmailNotificationService;
import com.innovazions.jbm.service.SMSNotificationService;
import com.innovazions.jbm.view.ActionStatus;
import com.innovazions.jbm.view.AppointmentView;
import com.innovazions.jbm.view.EventView;
import com.innovazions.jbm.vo.CalendarAppointmentDetailCalendarVO;
import com.innovazions.jbm.vo.DailyAppointmentCountVO;
import com.innovazions.jbm.vo.StaffAppointmentCountVO;

/**
 * Handles requests for the customer related actions.
 */
@Controller
public class AppointmentController extends AbstractController {

	private static final Logger logger = LoggerFactory
			.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private EmailNotificationService emailNotificationService;

	@Autowired
	@Qualifier(value = "smsNotificationServiceHTTPImpl")
	private SMSNotificationService smsNotificationService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private CustomerService customerService;

	List<CalendarAppointmentDetailCalendarVO> appointmentCountListForEventCalendar;

	@PostConstruct
	private void loadAppointmentCache() {
		appointmentCountListForEventCalendar = appointmentService
				.getAppointmentStaffNameForCalendar();

	}

	/*
	 * Customer Contract actions starts
	 */
	@RequestMapping(value = "/customerAppointmentListJSON")
	public List<AppointmentView> customerAppointmentListJSON(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model) {
		logger.info("AppoinmentController > customerAppointmentList");

		List<Appointment> appointmentList = appointmentService
				.getAppointmentListByFilter(appointmentView
						.convertViewToEntity(), "appointment_no",
						true);
		List<AppointmentView> appointmentViewList = new Appointment()
				.convertEntitiesToViews(appointmentList);
		return appointmentViewList;
	}

	@RequestMapping(value = "/detailedAppointmentCalendar")
	public String detailedAppointmentCalendar() {
		return "detailedCalendar";
	}

	@RequestMapping(value = "/customerAppointmentList")
	public String customerAppointmentList(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model) {
		logger.info("AppoinmentController > customerAppointmentList");
		appointmentView.setAppointmentStatus(appointmentView
				.getAppointmentStatus());
		List<Appointment> appointmentList = appointmentService
				.getAppointmentDetailedListByFilter(appointmentView
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
		/*
		 * try { if (appointmentView.getStartDate() != null) {
		 * appointmentView.setStartDate(CommonUtils
		 * .getJavaScriptDateObj(appointmentView.getStartDate())); } if
		 * (appointmentView.getEndDate() != null) {
		 * appointmentView.setEndDate(CommonUtils
		 * .getJavaScriptDateObj(appointmentView.getEndDate())); } } catch
		 * (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		model.addAttribute("appointmentListJSON", appointmentViewListJSON);
		model.addAttribute("appointmentView", appointmentView);
		return "customerAppointmentList";
	}

	@RequestMapping(value = "/customerAppoinmentComboListJSONAll", method = RequestMethod.GET)
	public @ResponseBody List<AppointmentView> customerAppoinmentComboListJSONAll() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getAllAppointmentComboList();
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerAppoinmentComboListJSONActive", method = RequestMethod.GET)
	public @ResponseBody List<AppointmentView> customerAppoinmentComboListJSONActive() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getActiveAppointmentComboList();
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerAppoinmentComboListJSON", method = RequestMethod.GET)
	public @ResponseBody List<AppointmentView> customerAppoinmentComboListJSON() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getAllAppointmentComboList();
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerPendingAppoinmentComboListJSON", method = RequestMethod.GET)
	public @ResponseBody List<AppointmentView> customerPendingAppoinmentComboListJSON() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getPendingAppointmentComboList();
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerAppointmentListJSON", method = RequestMethod.GET)
	public @ResponseBody List<AppointmentView> customerAppointmentListJSON() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getAppointmentListByFilter(null, "appointment_no",
						true);
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerApointmentAdd", method = RequestMethod.GET)
	public ModelAndView customerApointmentAdd(Locale locale, Model model) {
		logger.info("AppoinmentController > customerApointmentAdd");

		String appointmentCreationSMSFlag = PropertiesUtil.getProperty(JBMConstants.PROP_ENABLE_APPOINTMENT_CREATION_SMS);
		//return "customerApointmentAdd";
		return new ModelAndView("customerApointmentAdd", "appointmentCreationSMSFlag", appointmentCreationSMSFlag);
	}

	@RequestMapping(value = "/customerApointmentEdit", method = RequestMethod.GET)
	public ModelAndView customerApointmentEdit(Locale locale, Model model,
			@RequestParam("appointmentId") Long appointmentId) {
		logger.info("AppoinmentController > customerApointmentEdit");
		System.out.println(appointmentId);
		String appointmentUpdateSMSFlag = PropertiesUtil.getProperty(JBMConstants.PROP_ENABLE_APPOINTMENT_UPDATE_SMS);
		Appointment appointment = appointmentService
				.getAppoinmentDetailsByAppoinmentId(appointmentId);
		AppointmentView appointmentView = appointment.convertEntityToView();
		appointmentView.setSendSMSFlag(appointmentUpdateSMSFlag);
		return new ModelAndView("customerApointmentEdit", "appointmentView",
				appointmentView);
		// return "customerApointmentEdit";
	}

	@RequestMapping(value = "/saveCustomerAppoinment", method = RequestMethod.POST)
	public String saveCustomerAppoinment(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		System.out.println("Customer Id:" + appointmentView.getCustomerId()
				+ " Appointment No:" + appointmentView.getAppointmentNo());
		appointmentView.setLastModifiedDate(CommonUtils.getCurrentDate("Asia/Dubai"));
		appointmentView.setLastModifiedUser("SYSTEM");
		Appointment appointment = appointmentView.convertViewToEntity();
		String appointmentNo = commonService.getSequenceCodeByType(
				JBMConstants.SEQ_APPOINTMENT_NO,
				JBMConstants.PROP_PREFIX_APPOINTMENT_NO);
		appointment.setAppointmentNo(appointmentNo);
		appointment
				.setAppointmentStatus(JBMConstants.APPOINTMENT_STATUS_CREATED);
		appointment.setCustomer(customerService
				.getCustomerDetailsByCustomerId(appointment.getCustomer()
						.getId()));
		appointmentService.createAppointment(appointment);
		// send mail
		emailNotificationService
				.sendAppoinmentCreationEmailNotification(appointment);
		if (appointmentView.getSendSMSFlag() != null
				&& appointmentView.getSendSMSFlag().equals(
						JBMConstants.OPTION_YES)) {
			smsNotificationService
				.sendAppoinmentCreationSMSNotification(appointment);
		}
		redirectAttributes.addFlashAttribute("infoMessage",
				"Appointment Created : " + appointmentNo);
		return "redirect:customerApointmentAdd.html";
	}

	@RequestMapping(value = "/updateCustomerAppoinment", method = RequestMethod.POST)
	public String updateCustomerAppoinment(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		System.out.println("Appointment Id:" + appointmentView.getId()
				+ " Appointment Status:"
				+ appointmentView.getAppointmentStatus());
		appointmentView.setLastModifiedDate(CommonUtils.getCurrentDate("Asia/Dubai"));
		appointmentView.setLastModifiedUser("SYSTEM");
		Appointment appointment = appointmentView.convertViewToEntity();

		String message = "";
		appointment.setCustomer(customerService
				.getCustomerDetailsByCustomerId(appointment.getCustomer()
						.getId()));
		if (appointmentView.getAppointmentStatus().equals(
				JBMConstants.APPOINTMENT_STATUS_COMPLETED)) {
			// Do Validation
			// 1.Current time should be greater than end time
			appointmentService.updateAppointment(appointment);
			message = ActionMessages.STATUS_MESSAGE_APPOINTMENT_UPDATED;
			emailNotificationService
					.sendAppoinmentCompletionEmailNotification(appointment);
			if (appointmentView.getSendSMSFlagCompletion() != null
					&& appointmentView.getSendSMSFlagCompletion().equals(
							JBMConstants.OPTION_YES)) {
				smsNotificationService
				.sendAppoinmentCompletionSMSNotification(appointment);
			}
			
		} else if (appointmentView.getAppointmentStatus().equals(
				JBMConstants.APPOINTMENT_STATUS_CANCELLED)) {
			appointmentService.updateAppointment(appointment);
			emailNotificationService
					.sendAppoinmentCancellationEmailNotification(appointment);
			if (appointmentView.getSendSMSFlagCancellation() != null
					&& appointmentView.getSendSMSFlagCancellation().equals(
							JBMConstants.OPTION_YES)) {
				smsNotificationService
					.sendAppoinmentCancellationSMSNotification(appointment);
			}
			message = ActionMessages.STATUS_MESSAGE_APPOINTMENT_CANCELLED;
		}

		// send mail
		/*
		 * emailNotificationService
		 * .sendAppoinmentCreationEmailNotification(appointment);
		 * smsNotificationService
		 * .sendAppoinmentCreationSMSNotification(appointment);
		 */
		redirectAttributes.addFlashAttribute("actionMessage", message);
		return "redirect:customerAppointmentList.html";
	}

	@RequestMapping(value = "/modifyCustomerAppoinment", method = RequestMethod.POST)
	public String modifyCustomerAppoinment(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		System.out.println("Appointment Id:" + appointmentView.getId()
				+ " Appointment Status:"
				+ appointmentView.getAppointmentStatus());

		appointmentView.setLastModifiedDate(CommonUtils.getCurrentDate("Asia/Dubai"));
		appointmentView.setLastModifiedUser("SYSTEM");
		Appointment appointment = appointmentView.convertViewToEntity();

		String message = "";
		appointment.setCustomer(customerService
				.getCustomerDetailsByCustomerId(appointment.getCustomer()
						.getId()));

		appointmentService.modifyAppointmentDetails(appointment);

		emailNotificationService
				.sendAppoinmentUpdateEmailNotification(appointment);
		if (appointmentView.getSendSMSFlag() != null
				&& appointmentView.getSendSMSFlag().equals(
						JBMConstants.OPTION_YES)) {
			smsNotificationService.sendAppoinmentUpdateSMSNotification(appointment);
		}
		message = ActionMessages.STATUS_MESSAGE_APPOINTMENT_UPDATED;

		redirectAttributes.addFlashAttribute("actionMessage", message);
		return "redirect:customerAppointmentList.html";
	}

	@RequestMapping(value = "/customerAppointmentDetails")
	public String customerAppointmentDetails(Model model,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		logger.info("AppoinmentController > customerAppointmentDetails");
		String appointmentCompletionSMSFlag = PropertiesUtil.getProperty(JBMConstants.PROP_ENABLE_APPOINTMENT_COMPLETION_SMS);
		String appointmentCancellationSMSFlag = PropertiesUtil.getProperty(JBMConstants.PROP_ENABLE_APPOINTMENT_CANCELLATION_SMS);
		
		Integer appointmentId = (Integer) httpServletRequest.getSession()
				.getAttribute(
						JBMUIHelper.getLoggedInUserName(httpServletRequest,
								httpServletResponse) + "_SELECTED_APP_ID");
		model.addAttribute("appointmentId", appointmentId);
		model.addAttribute("appointmentCompletionSMSFlag", appointmentCompletionSMSFlag);
		model.addAttribute("appointmentCancellationSMSFlag", appointmentCancellationSMSFlag);
		httpServletRequest.getSession().setAttribute(
				JBMUIHelper.getLoggedInUserName(httpServletRequest,
						httpServletResponse) + "_SELECTED_APP_ID", null);
		return "customerAppointmentDetails";
	}

	@RequestMapping(value = "/customerAppointmentDetailsForSelectedId")
	public String customerAppointmentDetailsForSelectedId(Model model,
			@RequestParam("appointmentId") Integer appointmentId,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		logger.info("AppoinmentController > customerAppointmentDetails");
		httpServletRequest.getSession().setAttribute(
				JBMUIHelper.getLoggedInUserName(httpServletRequest,
						httpServletResponse) + "_SELECTED_APP_ID",
				appointmentId);
		return "redirect:customerAppointmentDetails.html";
	}

	@RequestMapping(value = "/getCustomerAppointmentDetails/{appointmentId}", method = RequestMethod.GET)
	public @ResponseBody AppointmentView getCustomerAppointmentDetails(
			@PathVariable Long appointmentId) {
		logger.info("AppoinmentController > getCustomerAppointmentDetails :"
				+ appointmentId);
		Appointment appointment = appointmentService
				.getAppoinmentDetailsByAppoinmentId(appointmentId);
		if (appointment != null) {
			System.out.println(CommonUtils.getDBDateTime(appointment
					.getStartDate()));
			System.out.println(CommonUtils.getDBDateTime(appointment
					.getEndDate()));
			return appointment.convertEntityToView();
		}
		return null;
	}

	@RequestMapping(value = "/staffAppoinmentCountListByDateJSON/{appointmentDate}", method = RequestMethod.GET)
	public @ResponseBody List<StaffAppointmentCountVO> staffAppoinmentCountListByDateJSON(
			@PathVariable Date appointmentDate) {
		logger.info("AppoinmentController > staffAppoinmentCountListJSON :"
				+ appointmentDate);
		System.out.println("appointmentDate : " + appointmentDate);
		if (appointmentDate == null) {
			appointmentDate = CommonUtils.getCurrentDate("Asia/Dubai");
		}
		List<StaffAppointmentCountVO> staffAppointmentCountList = appointmentService
				.getAllStaffAppointmentCountListByDate(CommonUtils
						.getMidnightDate(appointmentDate));
		return staffAppointmentCountList;
	}

	@RequestMapping(value = "/staffAppointmentCountListJSON/{staffId}", method = RequestMethod.GET)
	public @ResponseBody List<EventView> staffAppointmentCountListJSON(
			@PathVariable Long staffId) {
		logger.info("AppoinmentController > staffAppointmentCountListJSON :"
				+ staffId);
		System.out.println("staffId : " + staffId);
		List<DailyAppointmentCountVO> staffAppointmentCountList = new ArrayList<DailyAppointmentCountVO>();
		if (staffId != null && staffId > 0) {
			staffAppointmentCountList = appointmentService
					.getDailyAppointmentCountListByStaffId(staffId);
		} else {
			staffAppointmentCountList = appointmentService
					.getDailyAppointmentCountList();
		}
		return convertToEventListMonthView(staffAppointmentCountList);
	}

	@RequestMapping(value = "/getStaffAppointmentsTimeBreakupsJSON/{appointmentDate}", method = RequestMethod.GET)
	public @ResponseBody List<EventView> getStaffAppointmentsTimeBreakupsJSON(
			@PathVariable Date appointmentDate) {
		logger.info("AppoinmentController > getStaffAppointmentsTimeBreakupsJSON :"
				+ appointmentDate);
		// List<DailyAppointmentCountVO> staffAppointmentCountList = new
		// ArrayList<DailyAppointmentCountVO>();
		/*
		 * staffAppointmentCountList = appointmentService
		 * .getStaffAppointmentsTimeBreakups(null);
		 */

		/*
		 * List<CalendarAppointmentDetailCalendarVO> appointmentCountList =
		 * appointmentService .getAppointmentStaffNameForCalendar();
		 */
		appointmentCountListForEventCalendar = appointmentService
				.getAppointmentStaffNameForCalendar();
		return convertToEventListDayWeekViewNew(appointmentCountListForEventCalendar);
	}

	private List<EventView> convertToEventListMonthView(
			List<DailyAppointmentCountVO> staffAppointmentCountList) {
		long i = 1l;
		List<EventView> eventViewList = new ArrayList<EventView>();
		for (DailyAppointmentCountVO dailyAppointmentCountVO : staffAppointmentCountList) {
			List<CalendarAppointmentDetailCalendarVO> staffNameList = appointmentService
					.getAppointmentStaffNameForCalendarByDate(dailyAppointmentCountVO
							.getAppointmentDate());
			StringBuffer staffNameTooltip = new StringBuffer("");
			for (CalendarAppointmentDetailCalendarVO calendarAppointmentDetailCalendarVO : staffNameList) {
				staffNameTooltip
						.append(calendarAppointmentDetailCalendarVO
								.getEmployeeName())
						.append(" [")
						.append(calendarAppointmentDetailCalendarVO
								.getAppointmentCount()).append("]")
						.append("\n");
			}
			eventViewList.add(new EventView(i, dailyAppointmentCountVO
					.getAppointmentCount() + "", CommonUtils
					.getJavaScriptDateTime(dailyAppointmentCountVO
							.getAppointmentDate()), true, staffNameTooltip
					.toString()));
		}
		return eventViewList;
	}

	private List<EventView> convertToEventListDayWeekView(
			List<DailyAppointmentCountVO> staffAppointmentCountList) {
		long i = 1l;
		List<EventView> eventViewList = new ArrayList<EventView>();

		for (DailyAppointmentCountVO dailyAppointmentCountVO : staffAppointmentCountList) {

			List<CalendarAppointmentDetailCalendarVO> staffNameList = appointmentService
					.getAppointmentStaffNameForCalendarBetweenDate(
							dailyAppointmentCountVO.getStartDate(),
							dailyAppointmentCountVO.getEndDate());
			StringBuffer staffNameTooltip = new StringBuffer("");
			StringBuffer eventDescription = new StringBuffer("");
			for (CalendarAppointmentDetailCalendarVO calendarAppointmentDetailCalendarVO : staffNameList) {
				staffNameTooltip.append(
						calendarAppointmentDetailCalendarVO.getEmployeeName())
						.append("\n");
				eventDescription.append(
						calendarAppointmentDetailCalendarVO.getEmployeeName())
						.append("<br/>");
			}

			eventViewList.add(new EventView(i++, dailyAppointmentCountVO
					.getAppointmentCount() + "", CommonUtils
					.getJavaScriptDateTime(dailyAppointmentCountVO
							.getStartDate()),
					CommonUtils.getJavaScriptDateTime(dailyAppointmentCountVO
							.getEndDate()), false, staffNameTooltip.toString(),
					eventDescription.toString()));
		}
		return eventViewList;
	}

	private List<EventView> convertToEventListDayWeekViewNew(
			List<CalendarAppointmentDetailCalendarVO> appointmentCountList) {
		long i = 1l;
		List<EventView> eventViewList = new ArrayList<EventView>();

		for (CalendarAppointmentDetailCalendarVO calendarAppointmentDetailCalendarVO : appointmentCountList) {

			StringBuffer staffNameTooltip = new StringBuffer("");
			StringBuffer eventDescription = new StringBuffer("");
			staffNameTooltip.append(calendarAppointmentDetailCalendarVO
					.getEmployeeName());
			eventDescription.append(calendarAppointmentDetailCalendarVO
					.getEmployeeName());

			eventViewList
					.add(new EventView(
							i++,
							calendarAppointmentDetailCalendarVO
									.getAppointmentCount() + "",
							CommonUtils
									.getJavaScriptDateTime(calendarAppointmentDetailCalendarVO
											.getStartDate()),
							CommonUtils
									.getJavaScriptDateTime(calendarAppointmentDetailCalendarVO
											.getEndDate()), false,
							staffNameTooltip.toString(), eventDescription
									.toString()));
		}
		return eventViewList;
	}

	@RequestMapping(value = "/checkStaffAppointmentSlotsForEdit", method = RequestMethod.POST)
	public @ResponseBody String checkStaffAppointmentSlotsForEdit(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model) {
		System.out.println("Appointment Date: "
				+ appointmentView.getAppointmentDate() + "Start Time:"
				+ appointmentView.getStartTime() + " End Time:"
				+ appointmentView.getEndTime() + " Staff Id:"
				+ appointmentView.getEmployeeId());

		try {
			List<Appointment> appointmentsList = appointmentService
					.getStaffAppointmentsBetweenDatesNotId(appointmentView
							.getEmployeeId(), CommonUtils.addTimeStringToDate(
							appointmentView.getAppointmentDate(),
							appointmentView.getStartTime()), CommonUtils
							.addTimeStringToDate(
									appointmentView.getAppointmentDate(),
									appointmentView.getEndTime()),
							appointmentView.getId());
			if (appointmentsList.size() > 0) {
				return "false";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "/checkStaffAppointmentSlots", method = RequestMethod.POST)
	public @ResponseBody String checkStaffAppointmentSlots(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model) {
		System.out.println("Appointment Date: "
				+ appointmentView.getAppointmentDate() + "Start Time:"
				+ appointmentView.getStartTime() + " End Time:"
				+ appointmentView.getEndTime() + " Staff Id:"
				+ appointmentView.getEmployeeId());

		try {
			List<Appointment> appointmentsList = appointmentService
					.getStaffAppointmentsBetweenDates(appointmentView
							.getEmployeeId(), CommonUtils.addTimeStringToDate(
							appointmentView.getAppointmentDate(),
							appointmentView.getStartTime()), CommonUtils
							.addTimeStringToDate(
									appointmentView.getAppointmentDate(),
									appointmentView.getEndTime()));
			if (appointmentsList.size() > 0) {
				return "false";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "/checkCustomerDuplicateAppointmentsForEdit", method = RequestMethod.POST)
	public @ResponseBody String checkCustomerDuplicateAppointmentsForEdit(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model) {
		System.out.println("Appointment Date: "
				+ appointmentView.getAppointmentDate() + "Start Time:"
				+ appointmentView.getStartTime() + " End Time:"
				+ appointmentView.getEndTime() + " Staff Id:"
				+ appointmentView.getCustomerAddressId());

		try {
			List<Appointment> appointmentsList = appointmentService
					.getCustomerAddressAppointmentsBetweenDatesNotId(
							appointmentView.getCustomerAddressId(), CommonUtils
									.addTimeStringToDate(appointmentView
											.getAppointmentDate(),
											appointmentView.getStartTime()),
							CommonUtils.addTimeStringToDate(
									appointmentView.getAppointmentDate(),
									appointmentView.getEndTime()),
							appointmentView.getId());
			if (appointmentsList.size() > 0) {
				return "false";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "/checkCustomerDuplicateAppointments", method = RequestMethod.POST)
	public @ResponseBody String checkCustomerDuplicateAppointments(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, Model model) {
		System.out.println("Appointment Date: "
				+ appointmentView.getAppointmentDate() + "Start Time:"
				+ appointmentView.getStartTime() + " End Time:"
				+ appointmentView.getEndTime() + " Staff Id:"
				+ appointmentView.getCustomerAddressId());

		try {
			List<Appointment> appointmentsList = appointmentService
					.getCustomerAddressAppointmentsBetweenDates(appointmentView
							.getCustomerAddressId(), CommonUtils
							.addTimeStringToDate(
									appointmentView.getAppointmentDate(),
									appointmentView.getStartTime()),
							CommonUtils.addTimeStringToDate(
									appointmentView.getAppointmentDate(),
									appointmentView.getEndTime()));
			if (appointmentsList.size() > 0) {
				return "false";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "/checkDuplicateInvoiceNo", method = RequestMethod.GET)
	public @ResponseBody String checkDuplicateInvoiceNo(
			@RequestParam String invoiceNo) {
		System.out.println("invoiceNo: " + invoiceNo);
		return appointmentService.isDuplicateInvoiceNo(invoiceNo) + "";
	}

	@RequestMapping(value = "/getCancelledAppointments/{customerId}", method = RequestMethod.GET)
	public @ResponseBody String getCancelledAppointments(
			@PathVariable Long customerId) {
		System.out.println("customerId: " + customerId);
		int offsetDays = new Integer(
				PropertiesUtil
						.getProperty(JBMConstants.PROP_CUSTOMER_CANCELLATION_OFFSET_DAYS));
		Date today = CommonUtils.getCurrentDate("Asia/Dubai");
		Date endDate = CommonUtils.getEndMidnightDate(today);
		Date startDate = CommonUtils.getPastDate(endDate, offsetDays);
		List<Appointment> appointmentList = appointmentService
				.getCustomerCancelledAppointmentBetweenDates(customerId,
						startDate, endDate);
		if (appointmentList.size() > 0) {
			StringBuffer cancelledAppointmentList = new StringBuffer(
					"<table class='bordered'>");
			cancelledAppointmentList.append("<tr>");
			cancelledAppointmentList.append("<th>");
			cancelledAppointmentList.append("Code");
			cancelledAppointmentList.append("</th>");
			cancelledAppointmentList.append("<th>");
			cancelledAppointmentList.append("Date");
			cancelledAppointmentList.append("</th>");
			cancelledAppointmentList.append("<th>");
			cancelledAppointmentList.append("Cancellation Reason");
			cancelledAppointmentList.append("</th>");
			cancelledAppointmentList.append("</tr>");
			for (Appointment appointment : appointmentList) {
				cancelledAppointmentList.append("<tr>");
				cancelledAppointmentList.append("<td>");
				cancelledAppointmentList.append(
						appointment.getAppointmentDate()).append("<br/>");
				cancelledAppointmentList.append("</td>");
				cancelledAppointmentList.append("<td>");
				cancelledAppointmentList.append(appointment.getAppointmentNo());
				cancelledAppointmentList.append("</td>");
				cancelledAppointmentList.append("<td>");
				cancelledAppointmentList.append(appointment
						.getCancellationReason());
				cancelledAppointmentList.append("</td>");
				cancelledAppointmentList.append("</tr>");

			}
			cancelledAppointmentList.append("</table>");
			return cancelledAppointmentList.toString();
		}
		return "";
	}

	/*
	 * @RequestMapping(value = "/checkAppointmentEndTime", method =
	 * RequestMethod.GET) public @ResponseBody boolean
	 * checkAppointmentEndTime(@RequestParam Long appointmentId,
	 * 
	 * @RequestParam Date appointmentDate, @RequestParam String endTime) { try {
	 * Date endDate = CommonUtils.addTimeStringToDate(appointmentDate, endTime);
	 * int res = endDate.compareTo(CommonUtils.getCurrentDate("Asia/Dubai")); if (res > 0) { return false; }
	 * else { return true; } } catch (ParseException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return false; }
	 */

	boolean checkAppointmentEndTime(Long appointmentId, Date appointmentDate,
			String endTime) {
		try {
			Date endDate = CommonUtils.addTimeStringToDate(appointmentDate,
					endTime);
			int res = endDate.compareTo(CommonUtils.getCurrentDate("Asia/Dubai"));
			if (res > 0) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	boolean checkAppointmentEndTimeForCancellation(Long appointmentId,
			Date appointmentDate, String startTime) {
		try {
			Date startDate = CommonUtils.addTimeStringToDate(appointmentDate,
					startTime);
			int res = startDate.compareTo(CommonUtils.getCurrentDate("Asia/Dubai"));
			if (res > 0) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean checkForDuplicateCustomerAppointment(
			Long customerAddressId, Date startDate, Date endDate) {
		List<Appointment> customerAppointment = appointmentService
				.findCustomerAppointmentByAddressAndDate(customerAddressId,
						startDate, endDate);
		if (customerAppointment.size() > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/validateAppointmentUpdate", method = RequestMethod.GET)
	public @ResponseBody ActionStatus validateAppointmentUpdate(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result) {
		Appointment appointment = appointmentView.convertViewToEntity();

		StringBuffer message = new StringBuffer("");
		boolean hasErrors = false;
		ActionStatus actionStatus = null;

		// Check for valid end date
		boolean isValidEndTime = checkAppointmentEndTime(appointment.getId(),
				appointmentView.getAppointmentDate(),
				appointmentView.getEndTime());
		if (!isValidEndTime) {
			hasErrors = true;
			message.append("Appointment end date/time cannot be greater than current date/time");
		}

		if (hasErrors) {
			actionStatus = CommonUtils.getErrorActionStatus(message.toString());
		} else {
			actionStatus = CommonUtils.getSuccessActionStatus("");
		}

		return actionStatus;
	}

	@RequestMapping(value = "/validateAppointmentUpdateForCancellation", method = RequestMethod.GET)
	public @ResponseBody ActionStatus validateAppointmentCancellation(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result) {
		Appointment appointment = appointmentView.convertViewToEntity();

		StringBuffer message = new StringBuffer("");
		boolean hasErrors = false;
		ActionStatus actionStatus = null;

		// Check for valid end date
		boolean isValidEndTime = checkAppointmentEndTimeForCancellation(
				appointment.getId(), appointmentView.getAppointmentDate(),
				appointmentView.getStartTime());
		if (!isValidEndTime) {
			hasErrors = true;
			message.append("Appointment cannot be cancelled since appointment start date is less than current date/time !");
		}

		if (hasErrors) {
			actionStatus = CommonUtils.getErrorActionStatus(message.toString());
		} else {
			actionStatus = CommonUtils.getSuccessActionStatus("");
		}

		return actionStatus;
	}

	@RequestMapping(value = "/validateAppointmentSave", method = RequestMethod.GET)
	public @ResponseBody ActionStatus validateAppointmentSave(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result) {

		ActionStatus actionStatus = null;

		Date startDate;
		try {
			// startDate = CommonUtils.addTimeStringToDate(
			// appointmentView.getAppointmentDate(),
			// appointmentView.getStartTime());
			// int res = startDate.compareTo(CommonUtils.getCurrentDate("Asia/Dubai"));
			// if (res < 0) {
			// actionStatus = CommonUtils
			// .getErrorActionStatus("Appointment Start Date cannot be less than current time !");
			// } else {
			// actionStatus = CommonUtils.getSuccessActionStatus("");
			// }
			actionStatus = CommonUtils.getSuccessActionStatus("");

		} catch (Exception e) {
			e.printStackTrace();
			actionStatus = CommonUtils
					.getErrorActionStatus("Unexpected error occured, please contact support !");
		}

		return actionStatus;
	}
}
