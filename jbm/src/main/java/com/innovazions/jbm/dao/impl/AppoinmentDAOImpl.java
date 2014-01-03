package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.AppointmentDAO;
import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.jdbc.mapper.AppoinmentRowMapper;

@Repository
public class AppoinmentDAOImpl implements AppointmentDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;

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
	public List<Appointment> getAppointmentListByFilter(Appointment Appointment) {

		String sql = "SELECT a.id as appoinment_id, a.appointment_no as appointment_no, "
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

		StringBuffer whereClause = new StringBuffer("");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Appointment> appointmentList = new ArrayList<Appointment>();

		appointmentList = jdbcTemplate.query(sql, new AppoinmentRowMapper());
		return appointmentList;
	}
}