package com.innovazions.jbm.view;

import java.util.List;

import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;

public class CustomerAddressView extends
		GenericView<CustomerAddressView, CustomerAddress> {

	private Long id;

	private String addressType;

	private String buildingName;

	private String flatNo;

	private Long areaId;

	private Long customerId;

	private Long cityId;

	private String areaName;

	private String cityName;

	private String remarks;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public CustomerAddress convertViewToEntity() {
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddressType(this.getAddressType());
		Area area = new Area();
		area.setId(this.getAreaId());
		customerAddress.setArea(area);
		customerAddress.setBuildingName(this.getBuildingName());
		Customer customer = new Customer();
		customer.setId(this.getCustomerId());
		customerAddress.setCustomer(customer);
		customerAddress.setFlatNo(this.getFlatNo());
		customerAddress.setLastModifiedDate(this.getLastModifiedDate());
		customerAddress.setLastModifiedUser(this.getLastModifiedUser());
		customerAddress.setRemarks(this.getRemarks());
		return customerAddress;
	}

	@Override
	public List<CustomerAddress> convertViewsToEntities(
			List<CustomerAddressView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getComboBoxText() {
		return "<div class='dottedBox'> <b>Type : </b>" + this.getAddressType()
				+ "<br/><b>Building : </b>" + this.getBuildingName()
				+ " <br/><b>Flat : </b>" + this.getFlatNo()
				+ "<br/><b>Location : </b>" + this.getAreaName()
				+ "<br/><b>City : </b>" + this.getCityName() + "</div>";
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
