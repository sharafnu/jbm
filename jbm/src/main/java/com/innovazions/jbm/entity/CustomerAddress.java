package com.innovazions.jbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	private String remarks;

	private Double latitude;
	
	private Double longitude;
	
	
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
		CustomerAddressView customerAddressView = new CustomerAddressView();
		customerAddressView.setAddressType(this.getAddressType());
		if (this.getArea() != null) {
			customerAddressView.setAreaId(this.getArea().getId());
			customerAddressView.setAreaName(this.getArea().getName());
			if (this.getArea().getCity() != null) {
				customerAddressView.setCityId(this.getArea().getCity().getId());
				customerAddressView.setCityName(this.getArea().getCity()
						.getName());
			}
		}
		customerAddressView.setBuildingName(this.getBuildingName());
		if (this.getCustomer() != null) {
			customerAddressView.setCustomerId(this.getCustomer().getId());
		}
		customerAddressView.setFlatNo(this.getFlatNo());
		customerAddressView.setId(this.getId());
		customerAddressView.setLastModifiedDate(this.getLastModifiedDate());
		customerAddressView.setLastModifiedUser(this.getLastModifiedUser());
		customerAddressView.setRemarks(this.getRemarks());
		customerAddressView.setLatitude(this.getLatitude());
		customerAddressView.setLongitude(this.getLongitude());
		return customerAddressView;
	}

	@Override
	public List<CustomerAddressView> convertEntitiesToViews(
			List<CustomerAddress> entityList) {
		List<CustomerAddressView> addressViewList = new ArrayList<CustomerAddressView>();
		for (CustomerAddress customerAddress : entityList) {
			addressViewList.add(customerAddress.convertEntityToView());
		}
		return addressViewList;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}