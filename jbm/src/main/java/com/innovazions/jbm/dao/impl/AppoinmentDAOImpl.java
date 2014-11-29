package com.innovazions.jbm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.dao.AppointmentDAO;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.jdbc.mapper.AppointmentDetailRowMapper;
import com.innovazions.jbm.entity.jdbc.mapper.AppointmentRowMapper;
import com.innovazions.jbm.vo.CalendarAppointmentDetailCalendarVO;
import com.innovazions.jbm.vo.DailyAppointmentCountVO;
import com.innovazions.jbm.vo.StaffAppointmentCountVO;

@Repository
public class AppoinmentDAOImpl implements AppointmentDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;

	private static final String SELECT_APPOINTMENT_QUERY = "SELECT a.id as appoinment_id, a.appointment_no as appointment_no, "
			+ "a.appointment_date as appointment_date,a.start_date as start_date, a.end_date as end_date, a.customer_address_id as address_id, "
			+ "a.customer_id as customer_id, a.employee_id as employee_id, a.remarks as remarks, a.hours_spent as hours_spent, "
			+ "a.payable_amount as payable_amount, a.payment_status as payment_status, a.last_modified_date as last_modified_date, "
			+ "a.last_modified_user as last_modified_user, a.appointment_status as appointment_status, a.cancellation_reason as cancellation_reason, "
			+ "c.first_name as customer_first_name, c.last_name as customer_last_name, c.mobile_1 as mobile1, c.mobile_2 as mobile2, e.first_name as employee_first_name, ar.id as area_id, ar.name as area_name, "
			+ "ci.name as city_name, ci.id as city_id, ca.building_name, ca.flat_no, ca.address_type, ca.remarks as addressRemarks FROM appointment a inner join customer c on c.id=a.customer_id "
			+ "inner join employee e on e.id=a.employee_id "
			+ "inner join customer_address ca on ca.id=a.customer_address_id "
			+ "left outer join area ar on ca.area_id=ar.id "
			+ "left outer join city ci on ci.id=ar.city_id";
	
	private static final String SELECT_APPOINTMENT_DETAIL_QUERY = "SELECT a.id as appoinment_id, a.appointment_no as appointment_no, "
			+ "a.appointment_date as appointment_date,a.start_date as start_date, a.end_date as end_date, a.customer_address_id as address_id, "
			+ "a.customer_id as customer_id, a.employee_id as employee_id, a.remarks as remarks, a.hours_spent as hours_spent, "
			+ "a.payable_amount as payable_amount, a.payment_status as payment_status, a.last_modified_date as last_modified_date, "
			+ "a.last_modified_user as last_modified_user, a.appointment_status as appointment_status, a.cancellation_reason as cancellation_reason, "
			+ "c.first_name as customer_first_name, c.last_name as customer_last_name, c.mobile_1 as mobile1, c.mobile_2 as mobile2, "
			+ "e.first_name as employee_first_name, ar.id as area_id, ar.name as area_name, "
			+ "ci.name as city_name, ci.id as city_id, ca.building_name, ca.flat_no, ca.address_type, ca.remarks as addressRemarks, "
			+ "ap.amount_paid as amount_paid, ap.payment_mode as payment_mode,   "
			+ "invoice.amount as invoice_amount, invoice.invoice_no as invoice_no, invoice.invoice_date as  invoice_date "
			+ "FROM appointment a inner join customer c on c.id=a.customer_id "
			+ "inner join employee e on e.id=a.employee_id "
			+ "inner join customer_address ca on ca.id=a.customer_address_id "
			+ "left outer join area ar on ca.area_id=ar.id "
			+ "left outer join city ci on ci.id=ar.city_id "
			+ "left outer join appointment_payment ap on a.id=ap.appointment_id "
			+ "left outer join invoice invoice on a.id=invoice.appointment_id";

	private static final String SELECT_APPOINTMENT_BY_ID_QUERY = "SELECT a.id as appoinment_id, a.appointment_no as appointment_no, "
			+ "a.appointment_date as appointment_date,a.start_date as start_date, a.end_date as end_date, a.customer_address_id as address_id, "
			+ "a.customer_id as customer_id, a.employee_id as employee_id, a.remarks as remarks, a.hours_spent as hours_spent, "
			+ "a.payable_amount as payable_amount, a.payment_status as payment_status, a.last_modified_date as last_modified_date, "
			+ "a.last_modified_user as last_modified_user, a.appointment_status as appointment_status, a.cancellation_reason as cancellation_reason, "
			+ "c.first_name as customer_first_name, c.last_name as customer_last_name, c.mobile_1 as mobile1, c.mobile_2 as mobile2, e.first_name as employee_first_name, ar.id as area_id, ar.name as area_name, "
			+ "ci.name as city_name, ci.id as city_id, ca.building_name, ca.flat_no, ca.address_type, ca.remarks as addressRemarks FROM appointment a inner join customer c on c.id=a.customer_id "
			+ "inner join employee e on e.id=a.employee_id "
			+ "inner join customer_address ca on ca.id=a.customer_address_id "
			+ "left outer join area ar on ca.area_id=ar.id "
			+ "left outer join city ci on ci.id=ar.city_id where a.id=?";

	private static final String SELECT_APPOINTMENT_DETAILS_FOR_CALENDAR_BY_DATE = "SELECT a.appointment_no as appointment_no, a.remarks as remarks, "
			+ "a.appointment_status as appointment_status, "
			+ "c.first_name as customer_first_name, c.last_name as customer_last_name, e.first_name as employee_first_name, "
			+ "ar.name as area_name, ci.name as city_name, ca.building_name, ca.flat_no, ca.address_type FROM appointment a "
			+ "inner join customer c on c.id=a.customer_id "
			+ "inner join employee e on e.id=a.employee_id "
			+ "inner join customer_address ca on ca.id=a.customer_address_id "
			+ "left outer join area ar on ca.area_id=ar.id "
			+ "left outer join city ci on ci.id=ar.city_id where a.appointment_date=? and a.appointment_status <> ?";

	private static final String SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR_BY_DATE = "SELECT  distinct e.id, e.first_name as employee_first_name, "
			+ "count(1) as appointmentCount "
			+ "FROM appointment a inner join employee e on e.id=a.employee_id "
			+ "where a.appointment_date=? and a.appointment_status <> ? group by e.id, employee_first_name";

	private static final String SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR_START_AND_END_DATE = "SELECT  distinct e.id, e.first_name as employee_first_name, "
			+ "count(1) as appointmentCount "
			+ "FROM appointment a inner join employee e on e.id=a.employee_id "
			+ "where a.start_date=? and a.end_date=? and a.appointment_status <> ? group by e.id, employee_first_name";

	private static final String SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR = "select outerAppointment.start_date, outerAppointment.end_date, count(*) as appointmentCount, "
			+ "(SELECT  string_agg(e.first_name, ', ') as employee_names FROM appointment appointmentInner "
			+ "inner join employee e on e.id=appointmentInner.employee_id "
			+ "where appointmentInner.start_date=outerAppointment.start_date "
			+ "and appointmentInner.end_date=outerAppointment.end_date) from appointment outerAppointment "
			+ "where outerAppointment.appointment_status <> ?  "
			+ "group by outerAppointment.start_date, outerAppointment.end_date order by outerAppointment.start_date desc";

	@Override
	public long createAppointment(Appointment appointment) {
		System.out.println("Inserting Appointment..");
		final String sql = "INSERT INTO appointment(appointment_no, appointment_date, start_date, end_date, customer_address_id, "
				+ "customer_id, employee_id, remarks, last_modified_date, last_modified_user, "
				+ "appointment_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sequence = new PostgreSQLSequenceMaxValueIncrementer(dataSource,
				"appointment_id_seq");
		jdbcTemplate.update(
				sql,
				new Object[] {
						appointment.getAppointmentNo(),
						CommonUtils.getTimeStampFromDate(appointment
								.getAppointmentDate()),
						CommonUtils.getTimeStampFromDate(appointment
								.getStartDate()),
						CommonUtils.getTimeStampFromDate(appointment
								.getEndDate()),
						appointment.getCustomerAddress().getId(),
						appointment.getCustomer().getId(),
						appointment.getEmployee().getId(),
						appointment.getRemarks(),
						CommonUtils.getTimeStampFromDate(appointment
								.getLastModifiedDate()),
						appointment.getLastModifiedUser(),
						appointment.getAppointmentStatus() });
		return sequence.nextLongValue() - 1;
	}

	@Override
	public long updateAppointment(Appointment appointment) {
		String updateSQL = "update appointment set end_date=?, hours_spent=?, payable_amount=?, payment_status=?, appointment_status=?, cancellation_reason=? where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				updateSQL,
				new Object[] { appointment.getEndDate(),
						appointment.getHoursSpent(),
						appointment.getPayableAmount(),
						appointment.getPaymentStatus(),
						appointment.getAppointmentStatus(),
						appointment.getCancellationReason(),
						appointment.getId() });

		// Insert into payment table
		String insertPaymentSQL = "insert into appointment_payment(appointment_id, amount_paid, payment_mode) values (?, ?, ?)";
		jdbcTemplate.update(
				insertPaymentSQL,
				new Object[] { appointment.getId(),
						appointment.getPayableAmount(),
						appointment.getAppointmentPayment().getPaymentMode() });

		String insertInvoiceSQL = "insert into invoice(invoice_no, invoice_date, amount, appointment_id) values (?, ?, ?, ?)";

		jdbcTemplate.update(
				insertInvoiceSQL,
				new Object[] { appointment.getInvoice().getInvoiceNo(),
						appointment.getInvoice().getInoviceDate(),
						appointment.getPayableAmount(), appointment.getId() });

		return 0;
	}

	@Override
	public long deleteAppointment(Appointment Appointment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Appointment> getAppointmentListByFilter(Appointment appointment) {

		StringBuffer queryWithWhereClause = new StringBuffer(
				SELECT_APPOINTMENT_QUERY)
				.append(constructQueryWhereClause(appointment));

		queryWithWhereClause.append(" ORDER BY appointment_no desc");
		System.out.println("SELECT QUERY : " + queryWithWhereClause.toString());

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = new ArrayList<Appointment>();

		appointmentList = jdbcTemplate.query(queryWithWhereClause.toString(),
				new AppointmentRowMapper());
		return appointmentList;
	}
	
	@Override
	public List<Appointment> getAppointmentDetailedListByFilter(Appointment appointment) {

		StringBuffer queryWithWhereClause = new StringBuffer(
				SELECT_APPOINTMENT_DETAIL_QUERY)
				.append(constructQueryWhereClause(appointment));

		queryWithWhereClause.append(" ORDER BY appointment_no desc");
		System.out.println("SELECT QUERY : " + queryWithWhereClause.toString());

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = new ArrayList<Appointment>();

		appointmentList = jdbcTemplate.query(queryWithWhereClause.toString(),
				new AppointmentDetailRowMapper());
		return appointmentList;
	}

	@Override
	public Appointment getAppoinmentDetailsByAppoinmentId(Long appointmentId) {
		System.out.println("getAppoinmentDetailsByAppoinmentId :"
				+ SELECT_APPOINTMENT_BY_ID_QUERY);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = jdbcTemplate.query(
				SELECT_APPOINTMENT_BY_ID_QUERY, new Object[] { appointmentId },
				new AppointmentRowMapper());
		if (appointmentList.isEmpty()) {
			return null;
		} else {
			return appointmentList.get(0);
		}
	}

	private String constructQueryWhereClause(Appointment appointment) {
		StringBuffer whereQuery = new StringBuffer("");
		int argumentCount = 0;
		boolean searchById = false;
		if (appointment != null) {
			System.out.println(appointment.getId());
			if (appointment.getId() != null && appointment.getId() > 0) {
				searchById = true;
				whereQuery.append(" and a.id=").append(appointment.getId());
				argumentCount++;
			} else if (!CommonUtils.isEmpty(appointment.getAppointmentNo())) {
				whereQuery.append(" and a.appointment_no like ").append(
						addQuote("%"
								+ appointment.getAppointmentNo().toUpperCase()
								+ "%"));
				argumentCount++;
				searchById = true;
			}

			if (!searchById) {

				if (!CommonUtils.isEmpty(appointment.getAppointmentStatus())) {
					if (!appointment.getAppointmentStatus().equals(
							JBMConstants.APPOINTMENT_STATUS_ALL)) {
						String[] statusArr = appointment.getAppointmentStatus()
								.split(",");
						String statusStr = "";
						for (int i = 0; i < statusArr.length; i++) {
							statusStr = statusStr
									+ addQuote(statusArr[i].trim());
							if (i < (statusArr.length - 1)) {
								statusStr = statusStr + ",";
							}
						}
						whereQuery.append(" and a.appointment_status in (")
								.append(statusStr).append(")");
						argumentCount++;
					}
				} else {
					whereQuery.append(" and a.appointment_status=").append(
							addQuote(JBMConstants.APPOINTMENT_STATUS_CREATED));
					argumentCount++;
				}
				if (!CommonUtils.isEmpty(appointment.getPaymentStatus())) {
					whereQuery.append(" and a.payment_status=").append(
							addQuote(appointment.getPaymentStatus()));
					argumentCount++;
				}

				if (appointment.getCustomer() != null
						&& appointment.getCustomer().getId() != null
						&& appointment.getCustomer().getId() > 0) {
					whereQuery.append(" and a.customer_id=").append(
							appointment.getCustomer().getId());
					argumentCount++;
				}
				if (appointment.getEmployee() != null
						&& appointment.getEmployee().getId() != null
						&& appointment.getEmployee().getId() > 0) {
					whereQuery.append(" and a.employee_id=").append(
							appointment.getEmployee().getId());
					argumentCount++;
				}
				System.out.println(appointment.getStartDate());
				System.out.println(appointment.getEndDate());
				if (appointment.getStartDate() != null) {
					if (appointment.getEndDate() != null) {
						// between
						whereQuery
								.append(" and (appointment_date between ")
								.append(addQuote(CommonUtils
										.getDBDate(CommonUtils
												.getMidnightDate(appointment
														.getStartDate()))))
								.append(" and ")
								.append(addQuote(CommonUtils
										.getDBDate(CommonUtils
												.getEndMidnightDate(appointment
														.getEndDate()))))
								.append(")");
						argumentCount++;
					} else {
						// check greater than start date
						whereQuery.append(" and appointment_date >= ").append(
								addQuote(CommonUtils.getDBDate(CommonUtils
										.getMidnightDate(appointment
												.getStartDate()))));
						argumentCount++;
					}
				} else if (appointment.getEndDate() != null) {
					// check less than end date
					whereQuery.append(" and appointment_date <= ").append(
							addQuote(CommonUtils.getDBDate(CommonUtils
									.getEndMidnightDate(appointment
											.getEndDate()))));
					argumentCount++;
				}
			}
		}
		System.out.println("WHERE QUERY : " + whereQuery.toString());
		if (argumentCount > 0) {
			whereQuery.replace(0, 4, " where ");
		}
		System.out.println("WHERE QUERY : " + whereQuery.toString());
		return whereQuery.toString();
	}

	private String addQuote(String inputStr) {
		return "'" + inputStr + "'";
	}

	@Override
	public List<Appointment> getAllAppointmentComboList() {
		String sql = "select id as appoinment_id, appointment_no from appointment order by appointment_no desc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = jdbcTemplate.query(sql,
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setId(rs.getLong("appoinment_id"));
						appointment.setAppointmentNo(rs
								.getString("appointment_no"));
						return appointment;
					}
				});
		return appointmentList;
	}

	@Override
	public List<Appointment> getActiveAppointmentComboList() {
		String sql = "select id as appoinment_id, appointment_no from appointment where appointment_status ='Created' order by appointment_no desc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = jdbcTemplate.query(sql,
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setId(rs.getLong("appoinment_id"));
						appointment.setAppointmentNo(rs
								.getString("appointment_no"));
						return appointment;
					}
				});
		return appointmentList;
	}

	public static void main(String[] args) {
		System.out.println(SELECT_APPOINTMENT_QUERY);
	}

	@Override
	public List<Appointment> getPendingAppointmentComboList() {
		String sql = "select id as appoinment_id, appointment_no from appointment where appointment_status=? order by appointment_no desc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = jdbcTemplate.query(sql,
				new Object[] { JBMConstants.APPOINTMENT_STATUS_CREATED },
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setId(rs.getLong("appoinment_id"));
						appointment.setAppointmentNo(rs
								.getString("appointment_no"));
						return appointment;
					}
				});
		return appointmentList;
	}

	@Override
	public List<StaffAppointmentCountVO> getAllStaffAppointmentCountListByDate(
			Date appointmentDate) {
		String sql = "select employee.id as employee_id, first_name, count(appointment.id) appointment_count from employee "
				+ "left join appointment on appointment.employee_id=employee.id and appointment_date =? and appointment_status=?"
				+ "group by employee.id, first_name order by appointment_count desc";

		/*
		 * String sql =
		 * "select employee_id, first_name, count(1) from appointment " +
		 * "inner join employee on employee.id=employee_id where to_char(appointment_date,'YYYY-DD-MM') =? group by employee_id, first_name"
		 * ;
		 */
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(sql);
		System.out.println("Date : " + CommonUtils.getDBDate(appointmentDate));
		List<StaffAppointmentCountVO> staffAppointmentCountList = jdbcTemplate
				.query(sql,
						new Object[] {
								CommonUtils
										.getTimeStampFromDate(appointmentDate),
								JBMConstants.APPOINTMENT_STATUS_CREATED },
						new RowMapper<StaffAppointmentCountVO>() {
							public StaffAppointmentCountVO mapRow(ResultSet rs,
									int rowNum) throws SQLException {
								StaffAppointmentCountVO appointmentCountVO = new StaffAppointmentCountVO();
								appointmentCountVO.setEmployeeId(rs.getLong(1));
								appointmentCountVO.setEmployeeName(rs
										.getString(2));
								appointmentCountVO.setAppointmentCount(rs
										.getInt(3));
								return appointmentCountVO;
							}
						});
		return staffAppointmentCountList;
	}

	@Override
	public List<DailyAppointmentCountVO> getDailyAppointmentCountList() {
		String sql = "select appointment_date, count(appointment.id) appointment_count from appointment where "
				+ "appointment_status=? "
				+ "group by appointment_date order by appointment_date desc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(sql);
		List<DailyAppointmentCountVO> appointmentCountList = jdbcTemplate
				.query(sql,
						new Object[] { JBMConstants.APPOINTMENT_STATUS_CREATED },
						new RowMapper<DailyAppointmentCountVO>() {
							public DailyAppointmentCountVO mapRow(ResultSet rs,
									int rowNum) throws SQLException {
								DailyAppointmentCountVO appointmentCountVO = new DailyAppointmentCountVO();
								appointmentCountVO.setAppointmentDate(rs
										.getDate(1));
								appointmentCountVO.setAppointmentCount(rs
										.getInt(2));
								return appointmentCountVO;
							}
						});
		return appointmentCountList;
	}

	@Override
	public List<DailyAppointmentCountVO> getDailyAppointmentCountListByStaffId(
			Long staffId) {
		String sql = "select appointment_date, count(appointment.id) appointment_count from appointment where "
				+ "employee_id=? and appointment_status=? "
				+ "group by appointment_date order by appointment_date desc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(sql);
		List<DailyAppointmentCountVO> appointmentCountList = jdbcTemplate
				.query(sql, new Object[] { staffId,
						JBMConstants.APPOINTMENT_STATUS_CREATED },
						new RowMapper<DailyAppointmentCountVO>() {
							public DailyAppointmentCountVO mapRow(ResultSet rs,
									int rowNum) throws SQLException {
								DailyAppointmentCountVO appointmentCountVO = new DailyAppointmentCountVO();
								appointmentCountVO.setAppointmentDate(rs
										.getDate(1));
								appointmentCountVO.setAppointmentCount(rs
										.getInt(2));
								return appointmentCountVO;
							}
						});
		return appointmentCountList;
	}

	@Override
	public List<Appointment> getStaffAppointmentsBetweenDates(Long staffId,
			Date fromDateTime, Date toDateTime) {
		/*
		 * select end_date, appointment_no from appointment where employee_id=1
		 * and ('2014-01-19 13:00:00' <end_date and '2014-01-19 13:00:00'
		 * >=start_date) or ('2014-01-19 17:00:00' >=start_date and '2014-01-19
		 * 17:00:00' <end_date)
		 */
		String sql = "select appointment_no, start_date, end_date from appointment where employee_id=? and "
				+ "((? >= start_date and  ? < end_date) or "
				+ "(? >start_date and  ? <=end_date)) and appointment_status <> ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Appointment> appointmentCountList = jdbcTemplate.query(
				sql,
				new Object[] { staffId,
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime),
						JBMConstants.APPOINTMENT_STATUS_CANCELLED },
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setAppointmentNo(rs.getString(1));
						appointment.setStartDate(rs.getDate(2));
						appointment.setEndDate(rs.getDate(3));
						return appointment;
					}
				});
		return appointmentCountList;
	}

	@Override
	public List<DailyAppointmentCountVO> getStaffAppointmentsTimeBreakups(
			Date appointmentDate) {
		String sql = null;
		if (appointmentDate != null) {
			sql = "select start_date, end_date, count(*) from appointment where "
					+ "appointment_date=? and appointment_status <> ? group by start_date, end_date"
					+ "order by start_date desc";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			System.out.println(sql);

			List<DailyAppointmentCountVO> appointmentCountList = jdbcTemplate
					.query(sql,
							new Object[] {
									CommonUtils
											.getTimeStampFromDate(appointmentDate),
									JBMConstants.APPOINTMENT_STATUS_CANCELLED },
							new RowMapper<DailyAppointmentCountVO>() {
								public DailyAppointmentCountVO mapRow(
										ResultSet rs, int rowNum)
										throws SQLException {
									DailyAppointmentCountVO appointmentCountVO = new DailyAppointmentCountVO();
									appointmentCountVO.setStartDate(rs
											.getTimestamp(1));
									appointmentCountVO.setEndDate(rs
											.getTimestamp(2));
									appointmentCountVO.setAppointmentCount(rs
											.getInt(3));
									return appointmentCountVO;
								}
							});
			return appointmentCountList;
		} else {
			sql = "select start_date, end_date, count(*) from appointment where appointment_status <> ? group by start_date, end_date "
					+ "order by start_date desc";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			System.out.println(sql);

			List<DailyAppointmentCountVO> appointmentCountList = jdbcTemplate
					.query(sql,
							new Object[] { JBMConstants.APPOINTMENT_STATUS_CANCELLED },
							new RowMapper<DailyAppointmentCountVO>() {
								public DailyAppointmentCountVO mapRow(
										ResultSet rs, int rowNum)
										throws SQLException {
									DailyAppointmentCountVO appointmentCountVO = new DailyAppointmentCountVO();
									appointmentCountVO.setStartDate(rs
											.getTimestamp(1));
									appointmentCountVO.setEndDate(rs
											.getTimestamp(2));
									appointmentCountVO.setAppointmentCount(rs
											.getInt(3));
									return appointmentCountVO;
								}
							});
			return appointmentCountList;

		}
	}

	@Override
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentDetailsForCalendarByDate(
			Date appointmentDate) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(SELECT_APPOINTMENT_DETAILS_FOR_CALENDAR_BY_DATE);
		List<CalendarAppointmentDetailCalendarVO> appointmentDetailList = jdbcTemplate
				.query(SELECT_APPOINTMENT_DETAILS_FOR_CALENDAR_BY_DATE,
						new Object[] {
								CommonUtils
										.getTimeStampFromDate(appointmentDate),
								JBMConstants.APPOINTMENT_STATUS_CANCELLED },
						new RowMapper<CalendarAppointmentDetailCalendarVO>() {
							public CalendarAppointmentDetailCalendarVO mapRow(
									ResultSet rs, int rowNum)
									throws SQLException {
								CalendarAppointmentDetailCalendarVO appointmentDetailVO = new CalendarAppointmentDetailCalendarVO();
								appointmentDetailVO.setAppointmentNo(rs
										.getString("appointment_no"));
								appointmentDetailVO.setRemarks(rs
										.getString("remarks"));
								appointmentDetailVO.setAppointmentStatus(rs
										.getString("appointment_status"));

								appointmentDetailVO.setCustomerName(rs
										.getString("customer_first_name")
										+ " "
										+ rs.getString("customer_last_name"));
								appointmentDetailVO.setEmployeeName(rs
										.getString("employee_first_name"));
								appointmentDetailVO.setAreaName(rs
										.getString("area_name"));
								appointmentDetailVO.setCityName(rs
										.getString("city_name"));
								appointmentDetailVO.setBuildingName(rs
										.getString("building_name"));
								appointmentDetailVO.setFlatNo(rs
										.getString("flat_no"));
								appointmentDetailVO.setAddressType(rs
										.getString("address_type"));
								return appointmentDetailVO;
							}
						});
		return appointmentDetailList;
	}

	@Override
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendarByDate(
			Date appointmentDate) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR_BY_DATE);
		List<CalendarAppointmentDetailCalendarVO> appointmentDetailList = jdbcTemplate
				.query(SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR_BY_DATE,
						new Object[] {
								CommonUtils
										.getTimeStampFromDate(appointmentDate),
								JBMConstants.APPOINTMENT_STATUS_CANCELLED },
						new RowMapper<CalendarAppointmentDetailCalendarVO>() {
							public CalendarAppointmentDetailCalendarVO mapRow(
									ResultSet rs, int rowNum)
									throws SQLException {
								CalendarAppointmentDetailCalendarVO appointmentDetailVO = new CalendarAppointmentDetailCalendarVO();
								appointmentDetailVO.setEmployeeName(rs
										.getString("employee_first_name"));
								appointmentDetailVO.setAppointmentCount(rs
										.getInt("appointmentCount"));
								return appointmentDetailVO;
							}
						});
		return appointmentDetailList;
	}

	@Override
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendar() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR);
		List<CalendarAppointmentDetailCalendarVO> appointmentDetailList = jdbcTemplate
				.query(SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR,
						new Object[] { JBMConstants.APPOINTMENT_STATUS_CANCELLED },
						new RowMapper<CalendarAppointmentDetailCalendarVO>() {
							public CalendarAppointmentDetailCalendarVO mapRow(
									ResultSet rs, int rowNum)
									throws SQLException {
								CalendarAppointmentDetailCalendarVO appointmentDetailVO = new CalendarAppointmentDetailCalendarVO();
								appointmentDetailVO.setStartDate(rs
										.getTimestamp("start_date"));
								appointmentDetailVO.setEndDate(rs
										.getTimestamp("end_date"));
								appointmentDetailVO.setEmployeeName(rs
										.getString("employee_names"));
								appointmentDetailVO.setAppointmentCount(rs
										.getInt("appointmentCount"));
								return appointmentDetailVO;
							}
						});
		return appointmentDetailList;
	}

	@Override
	/**
	 * @deprecated use {@link #new()} instead Use getAppointmentStaffNameForCalendar
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<CalendarAppointmentDetailCalendarVO> getAppointmentStaffNameForCalendarBetweenDate(
			Date startDate, Date endDate) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out
				.println(SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR_START_AND_END_DATE);
		List<CalendarAppointmentDetailCalendarVO> appointmentDetailList = jdbcTemplate
				.query(SELECT_APPOINTMENT_STAFF_NAME_FOR_CALENDAR_START_AND_END_DATE,
						new Object[] {
								CommonUtils.getTimeStampFromDate(startDate),
								CommonUtils.getTimeStampFromDate(endDate),
								JBMConstants.APPOINTMENT_STATUS_CANCELLED },
						new RowMapper<CalendarAppointmentDetailCalendarVO>() {
							public CalendarAppointmentDetailCalendarVO mapRow(
									ResultSet rs, int rowNum)
									throws SQLException {
								CalendarAppointmentDetailCalendarVO appointmentDetailVO = new CalendarAppointmentDetailCalendarVO();
								appointmentDetailVO.setEmployeeName(rs
										.getString("employee_first_name"));
								appointmentDetailVO.setAppointmentCount(rs
										.getInt("appointmentCount"));
								return appointmentDetailVO;
							}
						});
		return appointmentDetailList;
	}

	@Override
	public void cancelAppointment(Appointment appointment) {
		String updateSQL = "update appointment set appointment_status=?, cancellation_reason=? where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				updateSQL,
				new Object[] { JBMConstants.APPOINTMENT_STATUS_CANCELLED,
						appointment.getCancellationReason(),
						appointment.getId() });
	}

	@Override
	public boolean isDuplicateInvoiceNo(String invoiceNo) {
		String sql = "select count(1) from invoice where invoice_no= ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int invoiceCount = jdbcTemplate.queryForInt(sql,
				new Object[] { invoiceNo.trim() });
		System.out.println("invoiceCount : " + invoiceCount);
		if (invoiceCount > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Appointment> getCustomerCancelledAppointmentBetweenDates(
			long customerId, Date startDate, Date endDate) {
		String sql = "select appointment_no, appointment_date, cancellation_reason "
				+ "from appointment where appointment_status=? and customer_id=? and (appointment_date between ? and ?)"
				+ "order by appointment_no desc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = jdbcTemplate.query(sql,
				new Object[] { JBMConstants.APPOINTMENT_STATUS_CANCELLED,
						customerId,
						CommonUtils.getTimeStampFromDate(startDate),
						CommonUtils.getTimeStampFromDate(endDate) },
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setAppointmentNo(rs
								.getString("appointment_no"));
						appointment.setCancellationReason(rs
								.getString("cancellation_reason"));
						appointment.setAppointmentDate(rs
								.getDate("appointment_date"));
						return appointment;
					}
				});
		return appointmentList;
	}

	@Override
	public List<Appointment> findCustomerAppointmentByAddressAndDate(
			Long customerAddressId, Date startDate, Date endDate) {
		String sql = "select appointment_no, appointment_date from appointment "
				+ "where customer_address_id=? and appointment_status <> ? and and "
				+ "((? >= start_date and  ? < end_date) or "
				+ "(? >start_date and  ? <=end_date))";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = jdbcTemplate.query(
				sql,
				new Object[] { customerAddressId,
						JBMConstants.APPOINTMENT_STATUS_CANCELLED,
						CommonUtils.getTimeStampFromDate(startDate),
						CommonUtils.getTimeStampFromDate(endDate),
						CommonUtils.getTimeStampFromDate(startDate),
						CommonUtils.getTimeStampFromDate(endDate) },
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setAppointmentNo(rs
								.getString("appointment_no"));
						appointment.setAppointmentDate(rs
								.getDate("appointment_date"));
						return appointment;
					}
				});
		return appointmentList;
	}

	@Override
	public List<Appointment> getStaffAppointmentsBetweenDatesNotId(
			Long staffId, Date fromDateTime, Date toDateTime, Long id) {

		String sql = "select appointment_no, start_date, end_date from appointment where employee_id=? and "
				+ "((? >= start_date and  ? < end_date) or "
				+ "(? >start_date and  ? <=end_date)) and id <> ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(sql);
		System.out.println(fromDateTime);
		System.out.println(toDateTime);
		System.out.println(CommonUtils.getTimeStampFromDate(fromDateTime));
		System.out.println(CommonUtils.getTimeStampFromDate(toDateTime));

		List<Appointment> appointmentCountList = jdbcTemplate.query(
				sql,
				new Object[] { staffId,
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime), id },
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setAppointmentNo(rs.getString(1));
						appointment.setStartDate(rs.getDate(2));
						appointment.setEndDate(rs.getDate(3));
						return appointment;
					}
				});
		return appointmentCountList;
	}

	@Override
	public void modifyAppointmentDetails(Appointment appointment) {
		String updateSQL = "update appointment set appointment_date=?, start_date=?, end_date=?, customer_address_id=?, "
				+ "employee_id=?, remarks=?, last_modified_date=?, last_modified_user=?  where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate
				.update(updateSQL,
						new Object[] {
								CommonUtils.getTimeStampFromDate(appointment
										.getAppointmentDate()),
								CommonUtils.getTimeStampFromDate(appointment
										.getStartDate()),
								CommonUtils.getTimeStampFromDate(appointment
										.getEndDate()),
								appointment.getCustomerAddress().getId(),
								appointment.getEmployee().getId(),
								appointment.getRemarks(),
								CommonUtils.getTimeStampFromDate(appointment
										.getLastModifiedDate()),
								appointment.getLastModifiedUser(),
								appointment.getId() });

	}

	@Override
	public List<Appointment> getCustomerAddressAppointmentsBetweenDates(
			Long customerAddressId, Date fromDateTime, Date toDateTime) {
		/*
		 * select end_date, appointment_no from appointment where employee_id=1
		 * and ('2014-01-19 13:00:00' <end_date and '2014-01-19 13:00:00'
		 * >=start_date) or ('2014-01-19 17:00:00' >=start_date and '2014-01-19
		 * 17:00:00' <end_date)
		 */
		String sql = "select appointment_no, start_date, end_date from appointment where customer_address_id=? and "
				+ "((? >= start_date and  ? < end_date) or "
				+ "(? >start_date and  ? <=end_date))";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(sql);
		System.out.println(fromDateTime);
		System.out.println(toDateTime);
		System.out.println(CommonUtils.getTimeStampFromDate(fromDateTime));
		System.out.println(CommonUtils.getTimeStampFromDate(toDateTime));

		List<Appointment> appointmentList = jdbcTemplate.query(
				sql,
				new Object[] { customerAddressId,
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime) },
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setAppointmentNo(rs.getString(1));
						appointment.setStartDate(rs.getDate(2));
						appointment.setEndDate(rs.getDate(3));
						return appointment;
					}
				});
		return appointmentList;
	}

	@Override
	public List<Appointment> getCustomerAddressAppointmentsBetweenDatesNotId(
			Long customerAddressId, Date fromDateTime, Date toDateTime, Long id) {
		/*
		 * select end_date, appointment_no from appointment where employee_id=1
		 * and ('2014-01-19 13:00:00' <end_date and '2014-01-19 13:00:00'
		 * >=start_date) or ('2014-01-19 17:00:00' >=start_date and '2014-01-19
		 * 17:00:00' <end_date)
		 */
		String sql = "select appointment_no, start_date, end_date from appointment where customer_address_id=? and "
				+ "((? >= start_date and  ? < end_date) or "
				+ "(? >start_date and  ? <=end_date)) and id <> ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(sql);
		System.out.println(fromDateTime);
		System.out.println(toDateTime);
		System.out.println(CommonUtils.getTimeStampFromDate(fromDateTime));
		System.out.println(CommonUtils.getTimeStampFromDate(toDateTime));

		List<Appointment> appointmentList = jdbcTemplate.query(
				sql,
				new Object[] { customerAddressId,
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(fromDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime),
						CommonUtils.getTimeStampFromDate(toDateTime), id },
				new RowMapper<Appointment>() {
					public Appointment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Appointment appointment = new Appointment();
						appointment.setAppointmentNo(rs.getString(1));
						appointment.setStartDate(rs.getDate(2));
						appointment.setEndDate(rs.getDate(3));
						return appointment;
					}
				});
		return appointmentList;
	}

}