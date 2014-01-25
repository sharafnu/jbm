package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.Appointment;
import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.entity.Employee;

public class AppointmentExtractor implements ResultSetExtractor<Appointment> {

	@Override
	public Appointment extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Appointment appointment = new Appointment();
		appointment.setId(rs.getLong("appoinment_id"));
		appointment.setAppointmentNo(rs.getString("appointment_no"));
		appointment.setAppointmentDate(rs.getDate("appointment_date"));
		appointment.setStartDate(rs.getTimestamp("start_date"));
		appointment.setEndDate(rs.getTimestamp("end_date"));

		Area area = new Area();
		area.setId(rs.getLong("area_id"));
		area.setName(rs.getString("area_name"));
		City city = new City();
		city.setId(rs.getLong("city_id"));
		city.setName(rs.getString("city_name"));
		area.setCity(city);
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setArea(area);
		customerAddress.setBuildingName(rs.getString("building_name"));
		customerAddress.setFlatNo(rs.getString("flat_no"));
		customerAddress.setAddressType(rs.getString("address_type"));
		appointment.setCustomerAddress(customerAddress);
		
		Customer customer = new Customer();
		customer.setId(rs.getLong("customer_id"));
		customer.setFirstName(rs.getString("customer_first_name"));
		customer.setLastName(rs.getString("customer_last_name"));
		appointment.setCustomer(customer);

		Employee employee = new Employee();
		employee.setId(rs.getLong("employee_id"));
		employee.setFirstName(rs.getString("employee_first_name"));
		appointment.setEmployee(employee);

		appointment.setRemarks(rs.getString("remarks"));
		appointment.setHoursSpent(rs.getInt("hours_spent"));
		appointment.setPayableAmount(rs.getDouble("payable_amount"));
		appointment.setPaymentStatus(rs.getString("payment_status"));
		appointment.setAppointmentStatus(rs.getString("appointment_status"));
		appointment.setCancellationReason(rs.getString("cancellation_reason"));

		appointment.setLastModifiedDate(rs.getDate("last_modified_date"));
		appointment.setLastModifiedUser(rs.getString("last_modified_user"));
		return appointment;
	}
}
