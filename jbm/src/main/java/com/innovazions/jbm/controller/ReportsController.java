package com.innovazions.jbm.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.common.JBMUIHelper;
import com.innovazions.jbm.common.ReportsHelper;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.service.AppointmentService;
import com.innovazions.jbm.service.CommonService;
import com.innovazions.jbm.service.EmployeeService;
import com.innovazions.jbm.service.impl.AccessManagerService;
import com.innovazions.jbm.view.AppointmentView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReportsController extends AbstractController {

	private static final Logger logger = LoggerFactory
			.getLogger(ReportsController.class);

	@Autowired
	private AccessManagerService accessManagerService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(value = "/appointmentReportList")
	public String appointmentReportList() {
		return "appointmentReportList";
	}

	@RequestMapping(value = "/dailyAppointmentsReportList")
	public String dailyAppointmentsReportList(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) String appointmentStatus,
			@RequestParam(required = false) String reportFormat,
			Model model) {

		logger.info("ReportsController > dailyAppointmentsReportList");
		String appointmentViewListJSON = "";

		AppointmentView appointmentView = new AppointmentView();
		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
			try {
				appointmentView.setStartDate(CommonUtils
						.parseDBDateDDMMYY(startDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			try {
				appointmentView.setEndDate(CommonUtils
						.parseDBDateDDMMYY(endDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			appointmentView.setAppointmentStatus(appointmentStatus);
		}
		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
			List<Appointment> appointmentList = appointmentService
					.getAppointmentListByFilter(
							appointmentView.convertViewToEntity(),
							"appointment_no", false);
			List<AppointmentView> appointmentViewList = new Appointment()
					.convertEntitiesToViews(appointmentList);
			ObjectMapper objectMapper = new ObjectMapper();
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
		}
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("appointmentListJSON", appointmentViewListJSON);
		model.addAttribute("selectedAppointmentStatus", appointmentStatus);
		model.addAttribute("reportFormat", reportFormat);
		return "dailyAppointmentsReport";

	}

	@RequestMapping(value = "/dailyAppointmentsReport")
	public void downloadDailyAppointmentsReport(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) String appointmentStatus,
			@RequestParam(required = false) String reportFormat) {

		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
			Map<String, Object> parameters = new HashMap<String, Object>();

			StringBuffer statusParam = new StringBuffer("");
			String[] statusArr = appointmentStatus.split(",");
			int index = 1;
			for (String aStatus : statusArr) {
				statusParam.append("'").append(aStatus.trim()).append("'");
				if (index < statusArr.length) {
					statusParam.append(",");
				}
				index++;
			}

			parameters.put("startDate", startDate);
			parameters.put("endDate", endDate);
			parameters.put("status", statusParam.toString());
			parameters.put("userId",
					JBMUIHelper.getLoggedInUserName(request, response));
			try {

				String reportName = "Appointment_Report_" + startDate + "_"
						+ endDate + "." + reportFormat;

				ReportsHelper.generateReport(request, response,
						getConnection(), parameters,
						"Appointments_Report_Date_Range", reportFormat,
						reportName);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "/staffWiseAppointmentsReportList")
	public String staffWiseAppointmentsReportList(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) Long employeeId,
			@RequestParam(required = false) String appointmentStatus,
			Model model) {

		String appointmentViewListJSON = "";

		AppointmentView appointmentView = new AppointmentView();
		try {
			appointmentView.setStartDate(CommonUtils
					.parseDBDateDDMMYY(startDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			appointmentView.setEndDate(CommonUtils.parseDBDateDDMMYY(endDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		appointmentView.setAppointmentStatus(appointmentStatus);
		appointmentView.setEmployeeId(employeeId);
		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)
				&& employeeId > 0) {
			// Find employee name
			Employee employee = employeeService.findEmployeeById(employeeId);
			if (employee != null && employee.getId() > 0) {
				List<Appointment> appointmentList = appointmentService
						.getAppointmentListByFilter(
								appointmentView.convertViewToEntity(),
								"appointment_no", true);
				List<AppointmentView> appointmentViewList = new Appointment()
						.convertEntitiesToViews(appointmentList);
				ObjectMapper objectMapper = new ObjectMapper();
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
			}

		}
		model.addAttribute("startDate", startDate);
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("endDate", endDate);
		model.addAttribute("appointmentListJSON", appointmentViewListJSON);
		model.addAttribute("selectedAppointmentStatus", appointmentStatus);
		return "staffWiseAppointmentsReport";

	}

	@RequestMapping(value = "/staffWiseAppointmentsReport")
	public String downloadStaffWiseAppointmentsReport(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) Long employeeId,
			@RequestParam(required = false) String appointmentStatus,
			@RequestParam(required = false) String reportFormat) {

		// Appointments_Report_Staff_Date_Range.jrxml

		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)
				&& employeeId > 0) {
			// Find employee name
			Employee employee = employeeService.findEmployeeById(employeeId);
			if (employee != null && employee.getId() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("staffId", employeeId);
				parameters.put("staffName", employee.getFirstName());
				parameters.put("startDate", startDate);
				parameters.put("endDate", endDate);

				StringBuffer statusParam = new StringBuffer("");
				String[] statusArr = appointmentStatus.split(",");
				int index = 1;
				for (String aStatus : statusArr) {
					statusParam.append("'").append(aStatus.trim()).append("'");
					if (index < statusArr.length) {
						statusParam.append(",");
					}
					index++;
				}
				parameters.put("status", statusParam.toString());

				parameters.put("userId",
						JBMUIHelper.getLoggedInUserName(request, response));
				try {
					String reportName = "Staff_Appointment_Report_"
							+ employee.getFirstName() + "_" + startDate + "_"
							+ endDate + ".pdf";
					reportName = reportName.replaceAll(" ", "_");
					reportName = reportName.replaceAll("-", "_");

					ReportsHelper.generateReport(request, response,
							getConnection(), parameters,
							"Appointments_Report_Staff_Date_Range",
							reportFormat, reportName);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "staffWiseAppointmentsReport";

	}

	// Monthly Appointment Report
	@RequestMapping(value = "/monthlyAppointmentReportList")
	public String monthlyAppointmentReportList(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String monthYear, Model model) {

		String appointmentViewListJSON = "";

		AppointmentView appointmentView = new AppointmentView();
		appointmentView
				.setAppointmentStatus(JBMConstants.APPOINTMENT_STATUS_ALL);
		if (!CommonUtils.isEmpty(monthYear)) {
			int year = Integer.parseInt(monthYear.substring(0, 4));
			int month = Integer.parseInt(monthYear.substring(5, 7)) - 1;

			Date startDate = CommonUtils.getMonthStartDate(month, year);
			Date endDate = CommonUtils.getMonthEndDate(month, year);
			appointmentView.setStartDate(startDate);
			appointmentView.setEndDate(endDate);
			List<Appointment> appointmentList = appointmentService
					.getAppointmentListByFilter(
							appointmentView.convertViewToEntity(),
							"appointment_no", false);
			List<AppointmentView> appointmentViewList = new Appointment()
					.convertEntitiesToViews(appointmentList);
			ObjectMapper objectMapper = new ObjectMapper();
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
		}

		model.addAttribute("monthYear", monthYear);
		model.addAttribute("appointmentListJSON", appointmentViewListJSON);
		return "monthlyAppointmentsReport";

	}

	@RequestMapping(value = "/downloadMonthlyAppointmentReport")
	public void downloadMonthlyAppointmentReport(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String reportFormat,
			@RequestParam(required = false) String monthYear) {

		if (!CommonUtils.isEmpty(monthYear)) {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("yearMonth", monthYear);
			String month = monthYear.split("-")[1];
			String year = monthYear.split("-")[0];
			parameters
					.put("month", new DateFormatSymbols().getMonths()[Integer
							.parseInt(month) - 1]);
			parameters.put("year", year);
			parameters.put("userId",
					JBMUIHelper.getLoggedInUserName(request, response));
			try {

				String reportName = "Monthly_Appointment_Report_" + monthYear
						+ "." + reportFormat;
				reportName = reportName.replaceAll(" ", "_");
				reportName = reportName.replaceAll("-", "_");
				ReportsHelper
						.generateReport(request, response, getConnection(),
								parameters, "Appointments_Report_Monthly",
								reportFormat, reportName);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private Connection getConnection() {
		try {
			return commonService.getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
