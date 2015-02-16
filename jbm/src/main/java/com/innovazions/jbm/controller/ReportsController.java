package com.innovazions.jbm.controller;

import java.io.IOException;
import java.io.OutputStream;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
			Model model) {

		logger.info("ReportsController > dailyAppointmentsReportList");
		String appointmentViewListJSON = "";

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : " + endDate);
		AppointmentView appointmentView = new AppointmentView();
		try {
			appointmentView.setStartDate(CommonUtils
					.parseDBDateDDMMYY(startDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			appointmentView.setEndDate(CommonUtils.parseDBDateDDMMYY(endDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		appointmentView.setAppointmentStatus(appointmentStatus);
		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
			List<Appointment> appointmentList = appointmentService
					.getAppointmentListByFilter(
							appointmentView.convertViewToEntity(), "appointment_no", false);
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
		return "dailyAppointmentsReport";

	}

	@RequestMapping(value = "/dailyAppointmentsReport")
	public String dailyAppointmentsReport(Locale locale, Principal principal,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) String appointmentStatus) {

		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
			JasperReport jasperReport = null;
			JasperDesign jasperDesign = null;
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
			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/classes/");
			try {
				jasperDesign = JRXmlLoader.load(path
						+ "/reports/Appointments_Report_Date_Range.jrxml");

				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				byte[] byteStream = JasperRunManager.runReportToPdf(
						jasperReport, parameters, getConnection());

				// set content attributes for the response
				response.setContentType("pdf");
				response.setContentLength(byteStream.length);

				// set headers for the response
				String headerKey = "Content-Disposition";
				String headerValue = String.format(
						"attachment; filename=\"%s\"", "Appointment_Report_"
								+ startDate + "_" + endDate + ".pdf");
				response.setHeader(headerKey, headerValue);

				// get output stream of the response
				OutputStream outStream = response.getOutputStream();

				outStream.write(byteStream, 0, byteStream.length);

				outStream.close();

			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "dailyAppointmentsReport";

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

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : " + endDate);
		String appointmentViewListJSON = "";

		AppointmentView appointmentView = new AppointmentView();
		try {
			appointmentView.setStartDate(CommonUtils
					.parseDBDateDDMMYY(startDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			appointmentView.setEndDate(CommonUtils.parseDBDateDDMMYY(endDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
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
								appointmentView.convertViewToEntity(), "appointment_no",
								true);
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
	public String staffWiseAppointmentsReport(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) Long employeeId,
			@RequestParam(required = false) String appointmentStatus) {

		// Appointments_Report_Staff_Date_Range.jrxml

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : " + endDate);
		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)
				&& employeeId > 0) {
			// Find employee name
			Employee employee = employeeService.findEmployeeById(employeeId);
			if (employee != null && employee.getId() > 0) {
				JasperReport jasperReport = null;
				JasperDesign jasperDesign = null;
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
				System.out.println("statusParam : " + statusParam.toString());

				parameters.put("status", statusParam.toString());

				parameters.put("userId",
						JBMUIHelper.getLoggedInUserName(request, response));
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/classes/");
				try {
					jasperDesign = JRXmlLoader
							.load(path
									+ "/reports/Appointments_Report_Staff_Date_Range.jrxml");

					jasperReport = JasperCompileManager
							.compileReport(jasperDesign);
					byte[] byteStream = JasperRunManager.runReportToPdf(
							jasperReport, parameters, getConnection());

					// set content attributes for the response
					response.setContentType("pdf");
					response.setContentLength(byteStream.length);

					// set headers for the response
					String headerKey = "Content-Disposition";
					String reportName = "Staff_Appointment_Report_"
							+ employee.getFirstName() + "_" + startDate + "_"
							+ endDate + ".pdf";
					reportName = reportName.replaceAll(" ", "_");
					reportName = reportName.replaceAll("-", "_");
					String headerValue = String.format(
							"attachment; filename=\"%s\"", reportName);
					response.setHeader(headerKey, headerValue);

					// get output stream of the response
					OutputStream outStream = response.getOutputStream();

					outStream.write(byteStream, 0, byteStream.length);

					outStream.close();

				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "staffWiseAppointmentsReport";

	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/downloadAppointmentReport")
	public void downloadAppointmentReport(Locale locale, Principal principal,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JasperReport jasperReport = null;
		JasperDesign jasperDesign = null;
		Map<String, Object> parameters = new HashMap<String, Object>();
		String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/");
		try {
			jasperDesign = JRXmlLoader.load(path
					+ "/reports/Appointment_Report_Date_Range.jrxml");

			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport,
					parameters, getConnection());

			// set content attributes for the response
			response.setContentType("pdf");
			response.setContentLength(byteStream.length);

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"Report.pdf");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			outStream.write(byteStream, 0, byteStream.length);

			outStream.close();

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			System.out.println(month + " : " + year);
			System.out.println(startDate + " : " + endDate);
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
	public String downloadMonthlyAppointmentReport(Locale locale,
			Principal principal, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String monthYear) {

		// Appointments_Report_Staff_Date_Range.jrxml

		if (!CommonUtils.isEmpty(monthYear)) {
			// Find employee name
			JasperReport jasperReport = null;
			JasperDesign jasperDesign = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("yearMonth", monthYear);
			String month = monthYear.split("-")[1];
			String year = monthYear.split("-")[0];		
			parameters.put("month", new DateFormatSymbols().getMonths()[Integer.parseInt(month)-1]);
			parameters.put("year", year);
			parameters.put("userId",
					JBMUIHelper.getLoggedInUserName(request, response));
			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/classes/");
			try {
				jasperDesign = JRXmlLoader
						.load(path
								+ "/reports/Appointments_Report_Monthly.jrxml");

				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				byte[] byteStream = JasperRunManager.runReportToPdf(
						jasperReport, parameters, getConnection());

				// set content attributes for the response
				response.setContentType("pdf");
				response.setContentLength(byteStream.length);

				// set headers for the response
				String headerKey = "Content-Disposition";
				String reportName = "Monthly_Appointment_Report_"
						+ monthYear + ".pdf";
				reportName = reportName.replaceAll(" ", "_");
				reportName = reportName.replaceAll("-", "_");
				String headerValue = String.format(
						"attachment; filename=\"%s\"", reportName);
				response.setHeader(headerKey, headerValue);

				// get output stream of the response
				OutputStream outStream = response.getOutputStream();

				outStream.write(byteStream, 0, byteStream.length);

				outStream.close();

			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return "staffWiseAppointmentsReport";

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
