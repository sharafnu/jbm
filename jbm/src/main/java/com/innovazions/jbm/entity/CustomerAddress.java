package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.List;

import com.innovazions.jbm.view.CustomerAddressView;

/**
 * The persistent class for the customer_address database table.
 * 
 */
public class CustomerAddress extends
		CoreEntity<CustomerAddress, CustomerAddressView> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String addressType;

	private String buildingName;

	private String flatNo;

	private Area area;

	private Customer customer;

	public CustomerAddress() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getBuildingName() {
		return this.buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFlatNo() {
		return this.flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public CustomerAddressView convertEntityToView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerAddressView> convertEntitiesToViews(
			List<CustomerAddress> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

}