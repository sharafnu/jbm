package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.dao.AppointmentDAO;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.jdbc.mapper.AppointmentRowMapper;

@Repository
public class AppoinmentDAOImpl implements AppointmentDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;

	private static final String SELECT_APPOINTMENT_QUERY = "SELECT a.id as appoinment_id, a.appointment_no as appointment_no, "
			+ "a.appointment_date as appointment_date, "
			+ "a.start_date as start_date, a.end_date as end_date, a.area_id as area_id, "
			+ "a.customer_id as customer_id, a.employee_id as employee_id, a.remarks as remarks, "
			+ "a.hours_spent as hours_spent, a.payable_amount as payable_amount, "
			+ "a.payment_status as payment_status, a.last_modified_date as last_modified_date, "
			+ "a.last_modified_user as last_modified_user, a.appointment_status as appointment_status, "
			+ "a.cancellation_reason as cancellation_reason, "
			+ "c.first_name as customer_first_name, c.last_name as customer_last_name, "
			+ "e.first_name as employee_first_name,"
			+ "ar.name as area_name, "
			+ "ci.name as city_name, ci.id as city_id "
			+ "FROM appointment a "
			+ "inner join customer c on c.id=a.customer_id "
			+ "inner join employee e on e.id=a.employee_id "
			+ "inner join area ar on ar.id=a.area_id "
			+ "inner join city ci on ci.id=ar.city_id";

	@Override
	public long createAppointment(Appointment appointment) {
		System.out.println("Inserting Appointment..");
		final String sql = "INSERT INTO appointment(appointment_no, appointment_date, start_date, area_id, "
				+ "customer_id, employee_id, remarks, last_modified_date, last_modified_user, "
				+ "appointment_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sequence = new PostgreSQLSequenceMaxValueIncrementer(dataSource,
				"appointment_id_seq");
		jdbcTemplate.update(
				sql,
				new Object[] { appointment.getAppointmentNo(),
						appointment.getAppointmentDate(),
						appointment.getStartDate(),
						appointment.getArea().getId(),
						appointment.getCustomer().getId(),
						appointment.getEmployee().getId(),
						appointment.getRemarks(),
						appointment.getLastModifiedDate(),
						appointment.getLastModifiedUser(),
						appointment.getAppointmentStatus() });
		return sequence.nextLongValue() - 1;
	}

	@Override
	public long updateAppointment(Appointment Appointment) {
		// TODO Auto-generated method stub
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
	public Appointment getAppoinmentDetailsByAppoinmentId(Long appoinmentId) {

		String sql = "select c.id, c.customer_code, c.first_name,c.last_name, c.mobile_1, c.mobile_2, c.mobile_3, "
				+ "c.email, c.preference_call, c.preference_email, c.preference_sms, c.last_modified_date, "
				+ "c.last_modified_user from customer c where c.id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = jdbcTemplate.query(sql,
				new Object[] { appoinmentId }, new AppointmentRowMapper());
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
				whereQuery.append(" and appoinment_id=").append(
						appointment.getId());
				argumentCount++;
			}
			if (appointment.getId() != null
					&& appointment.getArea().getId() > 0) {
				whereQuery.append(" and area_id=").append(
						appointment.getArea().getId());
				argumentCount++;
			}
			if (!CommonUtils.isEmpty(appointment.getAppointmentStatus())) {
				whereQuery.append(" and appointment_status=").append(
						addQuote(appointment.getAppointmentStatus()));
				argumentCount++;
			}
			if (!CommonUtils.isEmpty(appointment.getPaymentStatus())) {
				whereQuery.append(" and payment_status=").append(
						addQuote(appointment.getPaymentStatus()));
				argumentCount++;
			}
			if (appointment.getArea() != null
					&& appointment.getArea().getId() != null
					&& appointment.getArea().getId() > 0) {
				whereQuery.append(" and area_id=").append(
						appointment.getArea().getId());
				argumentCount++;
			}
			if (appointment.getCustomer() != null
					&& appointment.getCustomer().getId() != null
					&& appointment.getCustomer().getId() > 0) {
				whereQuery.append(" and customer_id=").append(
						appointment.getCustomer().getId());
				argumentCount++;
			}
			if (appointment.getEmployee() != null
					&& appointment.getEmployee().getId() != null
					&& appointment.getEmployee().getId() > 0) {
				whereQuery.append(" and employee_id=").append(
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

}