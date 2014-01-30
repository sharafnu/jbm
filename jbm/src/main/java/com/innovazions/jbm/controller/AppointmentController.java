package com.innovazions.jbm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.innovazions.jbm.common.ActionMessages;
import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.JBMUIHelper;
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
	private SMSNotificationService smsNotificationService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private CustomerService customerService;

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
		model.addAttribute("appointmentListJSON", appointmentViewListJSON);
		return "customerAppointmentList";
	}

	@RequestMapping(value = "/customerAppoinmentComboListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<AppointmentView> customerAppoinmentComboListJSON() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getAllAppointmentComboList();
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerPendingAppoinmentComboListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<AppointmentView> customerPendingAppoinmentComboListJSON() {
		logger.info("AppoinmentController > getCustomerAppoinmentListJSON");
		List<Appointment> appointmentList = appointmentService
				.getPendingAppointmentComboList();
		return new Appointment().convertEntitiesToViews(appointmentList);
	}

	@RequestMapping(value = "/customerAppointmentListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<AppointmentView> customerAppointmentListJSON() {
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
	public String saveCustomerAppoinment(
			@ModelAttribute("appointmentView") AppointmentView appointmentView,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		System.out.println("Customer Id:" + appointmentView.getCustomerId()
				+ " Appointment No:" + appointmentView.getAppointmentNo());
		appointmentView.setLastModifiedDate(new Date());
		appointmentView.setLastModifiedUser("SYSTEM");
		Appointment appointment = appointmentView.convertViewToEntity();
		String appointmentNo = commonService.getSequenceCodeByType(
				JBMConstants.SEQ_APPOINTMENT_NO,
				JBMConstants.PROP_PREFIX_APPOINTMENT_NO);
		appointment.setAppointmentNo(appointmentNo);
		appointment
				.setAppointmentStatus(JBMConstants.APPOINTMENT_STATUS_CREATED);
		appointmentService.createAppointment(appointment);
		// send mail
		/*
		 * emailNotificationService
		 * .sendAppoinmentCreationEmailNotification(appointment);
		 * smsNotificationService
		 * .sendAppoinmentCreationSMSNotification(appointment);
		 */
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
		appointmentView.setLastModifiedDate(new Date());
		appointmentView.setLastModifiedUser("SYSTEM");
		Appointment appointment = appointmentView.convertViewToEntity();

		ActionStatus actionStatus = null;
		appointment.setCustomer(customerService
				.getCustomerDetailsByCustomerId(appointment.getCustomer()
						.getId()));
		if (appointmentView.getAppointmentStatus().equals(
				JBMConstants.APPOINTMENT_STATUS_COMPLETED)) {
			// Do Validation
			// 1.Current time should be greater than end time
			appointmentService.updateAppointment(appointment);
			actionStatus = CommonUtils
					.getSuccessActionStatus(ActionMessages.STATUS_MESSAGE_APPOINTMENT_UPDATED);
		} else if (appointmentView.getAppointmentStatus().equals(
				JBMConstants.APPOINTMENT_STATUS_CANCELLED)) {
			appointmentService.updateAppointment(appointment);
			emailNotificationService
					.sendAppoinmentCancellationEmailNotification(appointment);
			smsNotificationService
					.sendAppoinmentCancellationSMSNotification(appointment);
			CommonUtils
					.getSuccessActionStatus(ActionMessages.STATUS_MESSAGE_APPOINTMENT_CANCELLED);
		}

		// send mail
		/*
		 * emailNotificationService
		 * .sendAppoinmentCreationEmailNotification(appointment);
		 * smsNotificationService
		 * .sendAppoinmentCreationSMSNotification(appointment);
		 */
		redirectAttributes.addFlashAttribute("actionStatus", actionStatus);
		return "redirect:customerAppointmentList.html";
	}

	@RequestMapping(value = "/customerAppointmentDetails")
	public String customerAppointmentDetails(Model model,
			HttpServletRequest httpServletRequest) {
		logger.info("AppoinmentController > customerAppointmentDetails");
		Integer appointmentId = (Integer) httpServletRequest.getSession()
				.getAttribute(
						JBMUIHelper.getLoggedInUserName(httpServletRequest)
								+ "_SELECTED_APP_ID");
		model.addAttribute("appointmentId", appointmentId);
		httpServletRequest.getSession().setAttribute(
				JBMUIHelper.getLoggedInUserName(httpServletRequest)
						+ "_SELECTED_APP_ID", null);
		return "customerAppointmentDetails";
	}

	@RequestMapping(value = "/customerAppointmentDetailsForSelectedId")
	public String customerAppointmentDetailsForSelectedId(Model model,
			@RequestParam("appointmentId") Integer appointmentId,
			HttpServletRequest httpServletRequest) {
		logger.info("AppoinmentController > customerAppointmentDetails");
		httpServletRequest.getSession().setAttribute(
				JBMUIHelper.getLoggedInUserName(httpServletRequest)
						+ "_SELECTED_APP_ID", appointmentId);
		return "redirect:customerAppointmentDetails.html";
	}

	@RequestMapping(value = "/getCustomerAppointmentDetails/{appointmentId}", method = RequestMethod.GET)
	public @ResponseBody
	AppointmentView getCustomerAppointmentDetails(
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
	public @ResponseBody
	List<StaffAppointmentCountVO> staffAppoinmentCountListByDateJSON(
			@PathVariable Date appointmentDate) {
		logger.info("AppoinmentController > staffAppoinmentCountListJSON :"
				+ appointmentDate);
		System.out.println("appointmentDate : " + appointmentDate);
		if (appointmentDate == null) {
			appointmentDate = new Date();
		}
		List<StaffAppointmentCountVO> staffAppointmentCountList = appointmentService
				.getAllStaffAppointmentCountListByDate(CommonUtils
						.getMidnightDate(appointmentDate));
		return staffAppointmentCountList;
	}

	@RequestMapping(value = "/staffAppointmentCountListJSON/{staffId}", method = RequestMethod.GET)
	public @ResponseBody
	List<EventView> staffAppointmentCountListJSON(@PathVariable Long staffId) {
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
	public @ResponseBody
	List<EventView> getStaffAppointmentsTimeBreakupsJSON(
			@PathVariable Date appointmentDate) {
		logger.info("AppoinmentController > getStaffAppointmentsTimeBreakupsJSON :"
				+ appointmentDate);
		List<DailyAppointmentCountVO> staffAppointmentCountList = new ArrayList<DailyAppointmentCountVO>();
		staffAppointmentCountList = appointmentService
				.getStaffAppointmentsTimeBreakups(null);
		return convertToEventListDayWeekView(staffAppointmentCountList);
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

	@RequestMapping(value = "/checkStaffAppointmentSlots", method = RequestMethod.POST)
	public @ResponseBody
	String checkStaffAppointmentSlots(
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

	@RequestMapping(value = "/checkDuplicateInvoiceNo", method = RequestMethod.GET)
	public @ResponseBody
	String checkDuplicateInvoiceNo(@RequestParam String invoiceNo) {
		System.out.println("invoiceNo: " + invoiceNo);
		return appointmentService.isDuplicateInvoiceNo(invoiceNo) + "";
	}

}
