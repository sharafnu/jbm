package com.innovazions.jbm.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportsHelper {
	/*
	 * To change this template, choose Tools | Templates and open the template
	 * in the editor.
	 */

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static void generateReport(HttpServletRequest request,
			HttpServletResponse response, Connection connection,
			Map<String, Object> parameters, String jasperReportFileName,
			String reportFormat, String reportFileName) throws IOException {

		try {

			JasperReport jasperReport = getCompiledReport(request, jasperReportFileName);

			if (reportFormat.equals("html")) {
				// Create JasperPrint object using the fillReport() method in
				// JasperManager class
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, parameters, connection);
				generateHtmlOutput(jasperPrint, request, response);
			} else if (reportFormat.equals("pdf")) {
				generatePDFOutput(response, parameters, jasperReport,
						connection, reportFileName);
			} else if (reportFormat.equals("xls")) {
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, parameters, connection);
				generateExcelOutput(jasperPrint, request, response, connection,
						reportFileName);
			} else if (reportFormat.equals("doc")) {
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, parameters, connection);
				generateWordOutput(jasperPrint, request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generateHtmlOutput(JasperPrint jasperPrint,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, JRException, ServletException {

		JRHtmlExporter exporter = new JRHtmlExporter();

		Float zoom = new Float(1.3);

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
				response.getWriter());
		exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
		exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING,
				"UTF-8");
		exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
		exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
		exporter.setParameter(
				JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				Boolean.TRUE);
		exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		exporter.exportReport();

		PrintWriter out = response.getWriter();
		out.println("<br/><br/>");

	}

	private static void generatePDFOutput(HttpServletResponse response,
			Map<String, Object> parameters, JasperReport jasperReport,
			Connection connection, String reportFileName) throws JRException,
			NamingException, SQLException, IOException {

		byte[] bytes = null;

		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters,
				connection);

		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				reportFileName);
		response.setHeader(headerKey, headerValue);

		response.getOutputStream().write(bytes, 0, bytes.length);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	private static void generateExcelOutput(JasperPrint jasperPrint,
			HttpServletRequest request, HttpServletResponse response,
			Connection connection, String reportFileName) throws JRException,
			IOException {

		if (jasperPrint != null) {
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					xlsReport);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.exportReport();
			// Send response
			byte[] bytes = xlsReport.toByteArray();

			/*******************************************************************
			 * Pour afficher une boîte de dialogue pour enregistrer le fichier
			 * sous le nom rapport.xls
			 ******************************************************************/
			String headerValue = String.format("attachment; filename=\"%s\"",
					reportFileName);
			String headerKey = "Content-Disposition";
			response.setHeader(headerKey, headerValue);

/*			response.addHeader("Content-disposition",
			  "attachment;filename=rapport.xls");*/
			 
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes, 0, bytes.length);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} else {
			Writer writer = response.getWriter();
			writer.write("No report to be displayed");
			response.setContentType("text/HTML");
		}
	}

	private static void generateWordOutput(JasperPrint jasperPrint,
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {

		if (jasperPrint != null) {
			ByteArrayOutputStream docxReport = new ByteArrayOutputStream();
			JRDocxExporter exporter = new JRDocxExporter();
			exporter.setParameter(JRDocxExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM,
					docxReport);
			exporter.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT,
					Boolean.TRUE);
			exporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING,
					"UTF-8");

			exporter.exportReport();
			// Send response
			byte[] bytes = docxReport.toByteArray();
			/*******************************************************************
			 * Pour afficher une boîte de dialogue pour enregistrer le fichier
			 * sous le nom rapport.xls
			 ******************************************************************/

			response.addHeader("Content-disposition",
					"attachment;filename=rapport.docx");
			response.setContentType("application/vnd.ms-word");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes, 0, bytes.length);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} else {
			Writer writer = response.getWriter();
			writer.write("No report to be displayed");
			response.setContentType("text/HTML");
		}
	}

	private static JasperReport getCompiledReport(HttpServletRequest request,
			String jasperReportFileName) throws JRException {

		String reportPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/");

		File reportFile = new File(reportPath + File.separator+jasperReportFileName
				+ ".jasper");

		// If compiled file is not found, then
		// compile XML template
		if (!reportFile.exists()) {
			JasperCompileManager.compileReportToFile(reportPath+File.separator
					+ jasperReportFileName + ".jrxml");
		}

		/*InputStream jasperfile = ReportsHelper.class.getClassLoader()
				.getResourceAsStream(reportFile.getPath());*/

		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(new File(reportFile.getPath()));
		
/*		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(jasperfile);*/

		return jasperReport;
	}

}