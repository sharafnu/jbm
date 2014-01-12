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
import com.innovazions.jbm.entity.jdbc.mapper.AppointmentRowMapper;
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
			+ "c.first_name as customer_first_name, c.last_name as customer_last_name, e.first_name as employee_first_name, ar.id as area_id, ar.name as area_name, "
			+ "ci.name as city_name, ci.id as city_id, ca.building_name, ca.flat_no, ca.address_type FROM appointment a inner join customer c on c.id=a.customer_id "
			+ "inner join employee e on e.id=a.employee_id "
			+ "inner join customer_address ca on ca.id=a.customer_address_id "
			+ "inner join area ar on ca.area_id=ar.id "
			+ "inner join city ci on ci.id=ar.city_id";

	private static final String SELECT_APPOINTMENT_BY_ID_QUERY = "SELECT a.id as appoinment_id, a.appointment_no as appointment_no, "
			+ "a.appointment_date as appointment_date,a.start_date as start_date, a.end_date as end_date, a.customer_address_id as address_id, "
			+ "a.customer_id as customer_id, a.employee_id as employee_id, a.remarks as remarks, a.hours_spent as hours_spent, "
			+ "a.payable_amount as payable_amount, a.payment_status as payment_status, a.last_modified_date as last_modified_date, "
			+ "a.last_modified_user as last_modified_user, a.appointment_status as appointment_status, a.cancellation_reason as cancellation_reason, "
			+ "c.first_name as customer_first_name, c.last_name as customer_last_name, e.first_name as employee_first_name, ar.id as area_id, ar.name as area_name, "
			+ "ci.name as city_name, ci.id as city_id, ca.building_name, ca.flat_no, ca.address_type FROM appointment a inner join customer c on c.id=a.customer_id "
			+ "inner join employee e on e.id=a.employee_id "
			+ "inner join customer_address ca on ca.id=a.customer_address_id "
			+ "inner join area ar on ca.area_id=ar.id "
			+ "inner join city ci on ci.id=ar.city_id where a.id=?";

	@Override
	public long createAppointment(Appointment appointment) {
		System.out.println("Inserting Appointment..");
		final String sql = "INSERT INTO appointment(appointment_no, appointment_date, start_date, customer_address_id, "
				+ "customer_id, employee_id, remarks, last_modified_date, last_modified_user, "
				+ "appointment_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

		System.out.println("SELECT QUERY : " + queryWithWhereClause.toString());

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = new ArrayList<Appointment>();

		appointmentList = jdbcTemplate.query(queryWithWhereClause.toString(),
				new AppointmentRowMapper());
		return appointmentList;
	}

	@Override
	public Appointment getAppoinmentDetailsByAppoinmentId(Long appointmentId) {

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
		if (appointment != null) {
			if (appointment.getId() != null && appointment.getId() > 0) {
				whereQuery.append(" and a.appoinment_id=").append(
						appointment.getId());
				argumentCount++;
			}
			if (!CommonUtils.isEmpty(appointment.getAppointmentStatus())) {
				whereQuery.append(" and a.appointment_status=").append(
						addQuote(appointment.getAppointmentStatus()));
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
			if (appointment.getStartDate() != null) {
				if (appointment.getEndDate() != null) {
					// between
					whereQuery
							.append(" and (appointment_date between ")
							.append(addQuote(CommonUtils
									.getMidnightDateStr(appointment
											.getStartDate())))
							.append(" and ")
							.append(addQuote(CommonUtils
									.getEndMidnightDateStr(appointment
											.getEndDate()))).append(")");
					argumentCount++;
				} else {
					// check greater than start date
					whereQuery.append(" and appointment_date >= ").append(
							addQuote(CommonUtils.getMidnightDateStr(appointment
									.getStartDate())));
					argumentCount++;
				}
			} else if (appointment.getEndDate() != null) {
				// check less than end date
				whereQuery.append(" and appointment_date <= ").append(
						addQuote(CommonUtils.getEndMidnightDateStr(appointment
								.getEndDate())));
				argumentCount++;
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
		String sql = "select id as appoinment_id, appointment_no from appointment order by appointment_no";
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
		String sql = "select id as appoinment_id, appointment_no from appointment where appointment_status=? order by appointment_no";
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
				+ "group by employee.id, first_name order by appointment_count asc";

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
}