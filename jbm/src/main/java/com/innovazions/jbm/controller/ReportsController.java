package com.innovazions.jbm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
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
import com.innovazions.jbm.common.JBMUIHelper;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.Employee;
import com.innovazions.jbm.service.AppointmentService;
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
	private AppointmentService appointmentService;

	
	@RequestMapping(value = "/appointmentReportList")
	public String appointmentReportList(){
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
					.getAppointmentListByFilter(appointmentView
							.convertViewToEntity());
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
			@RequestParam(required = false) String endDate) {

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : " + endDate);
		if (!CommonUtils.isEmpty(startDate) && !CommonUtils.isEmpty(endDate)) {
			JasperReport jasperReport = null;
			JasperDesign jasperDesign = null;
			Map parameters = new HashMap();
			parameters.put("startDate", startDate);
			parameters.put("endDate", endDate);
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

				byte[] buffer = new byte[1024];
				int bytesRead = -1;

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
						.getAppointmentListByFilter(appointmentView
								.convertViewToEntity());
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
			@RequestParam(required = false) Long employeeId) {

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
				Map parameters = new HashMap();
				parameters.put("staffId", employeeId);
				parameters.put("staffName", employee.getFirstName());
				parameters.put("startDate", startDate);
				parameters.put("endDate", endDate);
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

					byte[] buffer = new byte[1024];
					int bytesRead = -1;

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
		Map parameters = new HashMap();
		parameters.put("REPORT_DATE", "08-06-2014");
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

			byte[] buffer = new byte[1024];
			int bytesRead = -1;

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

	Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/maxmaidn_test";
		String user = "postgres";
		String pass = "admin123";
		String driver = "org.postgresql.Driver";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn;

		} catch (Exception ms) {
			ms.printStackTrace();
			return null;
		}
	}

}
