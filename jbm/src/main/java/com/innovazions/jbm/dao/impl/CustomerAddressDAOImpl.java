package com.innovazions.jbm.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.dao.CustomerAddressDAO;
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.entity.jdbc.mapper.CustomerAddressRowMapper;

@Repository
public class CustomerAddressDAOImpl implements CustomerAddressDAO {

	@Autowired
	private DataSource dataSource;

	private PostgreSQLSequenceMaxValueIncrementer sequence;

	@Override
	public long createCustomerAddress(CustomerAddress customerAddress) {
		System.out.println("Inserting CustomerAddress..");
		final String sql = "INSERT INTO customer_address (customer_id, area_id, building_name, flat_no, address_type, remarks, latitude, longitude, "
				+ "last_modified_date, last_modified_user) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?);";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sequence = new PostgreSQLSequenceMaxValueIncrementer(dataSource,
				"customer_address_id_seq");
		jdbcTemplate.update(
				sql,
				new Object[] { customerAddress.getCustomer().getId(),
						customerAddress.getArea().getId(),
						customerAddress.getBuildingName(),
						customerAddress.getFlatNo(),
						customerAddress.getAddressType(),
						customerAddress.getRemarks(),
						customerAddress.getLatitude(),
						customerAddress.getLongitude(),
						customerAddress.getLastModifiedDate(),
						customerAddress.getLastModifiedUser() });
		return sequence.nextLongValue() - 1;
	}

	@Override
	public long updateCustomerAddress(CustomerAddress customerAddress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteCustomerAddress(CustomerAddress customerAddress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomerAddress> getCustomerAddresssByCustomerId(Long customerId) {

		String sql = "select ca.id as address_id, ca.customer_id as customer_id, ca.area_id as area_id, "
				+ "a.name as area_name, c.name as city_name,c.id as city_id, ca.building_name as building_name, "
				+ "ca.flat_no as flat_no, ca.address_type as address_type, ca.remarks as remarks, "
				+ "ca.latitude as latitude, ca.longitude as longitude, ca.last_modified_date as last_modified_date, "
				+ "ca.last_modified_user as last_modified_user from customer_address ca "
				+ "left outer join area a on a.id=ca.area_id "
				+ "left outer join city c on c.id=a.city_id where ca.customer_id=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustomerAddress> customerList = jdbcTemplate.query(sql,
				new Object[] { customerId }, new CustomerAddressRowMapper());
		return customerList;
	}
}