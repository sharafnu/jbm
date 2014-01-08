package com.innovazions.jbm.view;

import java.util.List;

public class StaffAppoinmentInfoView extends
		GenericView<StaffAppoinmentInfoView, StaffAppoinmentInfoView> {

	
	public StaffAppoinmentInfoView(Long staffId, String staffName,
			Double appoinmentCount) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.appoinmentCount = appoinmentCount;
	}

	private Long staffId;

	private String staffName;

	private Double appoinmentCount;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Double getAppoinmentCount() {
		return appoinmentCount;
	}

	public void setAppoinmentCount(Double appoinmentCount) {
		this.appoinmentCount = appoinmentCount;
	}

	@Override
	public StaffAppoinmentInfoView convertViewToEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StaffAppoinmentInfoView> convertViewsToEntities(
			List<StaffAppoinmentInfoView> viewList) {
		// TODO Auto-generated method stub
		return null;
	}

}
