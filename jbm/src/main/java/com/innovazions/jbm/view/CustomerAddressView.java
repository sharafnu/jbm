package com.innovazions.jbm.view;

import java.util.List;

import com.innovazions.jbm.entity.CustomerAddress;

public class CustomerAddressView extends GenericView<CustomerAddressView, CustomerAddress>{

	private Long id;

	private String addressType;

	private String buildingName;

	private String flatNo;
	
	private Long areaId;
	
	private Long customerId;
	
	private String cityId;
	
	private String areaName;
	
	private String cityName;

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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerAddress> convertViewsToEntities(
			List<CustomerAddressView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
