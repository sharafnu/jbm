package com.innovazions.jbm.entity.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.City;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;

public class CustomerAddressExtractor implements
		ResultSetExtractor<CustomerAddress> {

	@Override
	public CustomerAddress extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setId(rs.getLong("address_id"));
		customerAddress.setAddressType(rs.getString("address_type"));
		customerAddress.setBuildingName(rs.getString("building_name"));
		customerAddress.setRemarks("remarks");
		customerAddress.setLatitude(rs.getDouble("latitude"));
		customerAddress.setLongitude(rs.getDouble("longitude"));
		Customer customer = new Customer();
		customer.setId(rs.getLong("customer_id"));
		customerAddress.setCustomer(customer);
		customerAddress.setFlatNo(rs.getString("flat_no"));
		Area area = new Area();
		area.setId(rs.getLong("area_id"));
		area.setName(rs.getString("area_name"));
		City city = new City();
		city.setId(rs.getLong("city_id"));
		city.setName(rs.getString("city_name"));
		area.setCity(city);
		customerAddress.setArea(area);
		customerAddress.setLastModifiedDate(rs.getDate("last_modified_date"));
		customerAddress.setLastModifiedUser(rs.getString("last_modified_user"));
		return customerAddress;
	}

}
