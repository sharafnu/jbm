package com.innovazions.jbm.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.common.JBMConstants;
import com.innovazions.jbm.entity.Area;
import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.entity.CustomerAddress;

public class CustomerAndAddressView extends
		GenericView<CustomerAndAddressView, Customer> {

	private Long id;

	private String customerCode;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile1;

	private String mobile2;

	private String landline;

	private Integer preferenceCall;

	private Integer preferenceEmail;

	private Integer preferenceSms;

	private String fullName;

	private String searchText;

	// Residence Address Details
	private Long residenceAreaId;

	private String residenceBuildingName;

	private String residenceFlatNo;

	private String residenceRemarks;

	private Double residenceLatitude;
	
	private Double residenceLongitude;
	
	// Office Address Details
	private Long officeAreaId;

	private String officeBuildingName;

	private String officeFlatNo;
	
	private String officeRemarks;

	private Double officeLatitude;
	
	private Double officeLongitude;
	
	public CustomerAndAddressView() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile1() {
		return this.mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return this.mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public Integer getPreferenceCall() {
		return this.preferenceCall;
	}

	public void setPreferenceCall(Integer preferenceCall) {
		this.preferenceCall = preferenceCall;
	}

	public Integer getPreferenceEmail() {
		return this.preferenceEmail;
	}

	public void setPreferenceEmail(Integer preferenceEmail) {
		this.preferenceEmail = preferenceEmail;
	}

	public Integer getPreferenceSms() {
		return this.preferenceSms;
	}

	public void setPreferenceSms(Integer preferenceSms) {
		this.preferenceSms = preferenceSms;
	}

	public String getFullName() {
		return ((this.getFirstName() != null ? this.getFirstName() + " " : "") + (this
				.getLastName() != null ? this.getLastName() : ""));
	}

	public String getComboBoxText() {
		return "<div class='dottedBox'> " + this.getFullName() + "<br/>"
				+ this.getCustomerCode() + "<br/>" + this.getMobile1()
				+ "</div>";
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public Customer convertViewToEntity() {
		Customer customer = new Customer();
		customer.setCustomerCode(this.getCustomerCode());
		customer.setEmail(this.getEmail());
		customer.setFirstName(this.getFirstName());
		customer.setId(this.getId());
		customer.setLastModifiedDate(this.getLastModifiedDate());
		customer.setLastModifiedUser(this.getLastModifiedUser());
		customer.setLastName(this.getLastName());
		customer.setMobile1(this.getMobile1());
		customer.setMobile2(this.getMobile2());
		customer.setLandline(this.getLandline());
		customer.setPreferenceCall(this.getPreferenceCall());
		customer.setPreferenceEmail(this.getPreferenceEmail());
		customer.setPreferenceSms(this.getPreferenceSms());

		List<CustomerAddress> customerAddressList = new ArrayList<CustomerAddress>();
		if (this.getResidenceAreaId() != null) {
			CustomerAddress residenceAddress = new CustomerAddress();
			residenceAddress.setCustomer(customer);
			residenceAddress
					.setAddressType(JBMConstants.ADDRESS_TYPE_RESIDENCE);
			Area residenceArea = new Area();
			residenceArea.setId(this.getResidenceAreaId());
			residenceAddress.setArea(residenceArea);
			residenceAddress.setBuildingName(this.getResidenceBuildingName());
			residenceAddress.setFlatNo(this.getResidenceFlatNo());
			residenceAddress.setLastModifiedUser("SYSTEM");
			residenceAddress.setLastModifiedDate(CommonUtils.getCurrentDate("Asia/Dubai"));
			residenceAddress.setRemarks(this.getResidenceRemarks());
			residenceAddress.setLatitude(this.getResidenceLatitude());
			residenceAddress.setLongitude(this.getResidenceLongitude());
			customerAddressList.add(residenceAddress);
		}

		if (this.getOfficeAreaId() != null) {
			CustomerAddress officeAddress = new CustomerAddress();
			officeAddress.setCustomer(customer);
			officeAddress.setAddressType(JBMConstants.ADDRESS_TYPE_OFFICE);
			Area officeArea = new Area();
			officeArea.setId(this.getOfficeAreaId());
			officeAddress.setArea(officeArea);
			officeAddress.setBuildingName(this.getOfficeBuildingName());
			officeAddress.setFlatNo(this.getOfficeFlatNo());
			officeAddress.setLastModifiedUser("SYSTEM");
			officeAddress.setLastModifiedDate(CommonUtils.getCurrentDate("Asia/Dubai"));
			officeAddress.setRemarks(this.getOfficeRemarks());
			officeAddress.setLatitude(this.getOfficeLatitude());
			officeAddress.setLongitude(this.getOfficeLongitude());
			customerAddressList.add(officeAddress);
		}
		customer.setCustomerAddressList(customerAddressList);
		return customer;
	}

	@Override
	public List<Customer> convertViewsToEntities(
			List<CustomerAndAddressView> viewList) {
		return null;
	}

	public Long getResidenceAreaId() {
		return residenceAreaId;
	}

	public void setResidenceAreaId(Long residenceAreaId) {
		this.residenceAreaId = residenceAreaId;
	}

	public String getResidenceBuildingName() {
		return residenceBuildingName;
	}

	public void setResidenceBuildingName(String residenceBuildingName) {
		this.residenceBuildingName = residenceBuildingName;
	}

	public String getResidenceFlatNo() {
		return residenceFlatNo;
	}

	public void setResidenceFlatNo(String residenceFlatNo) {
		this.residenceFlatNo = residenceFlatNo;
	}

	public Long getOfficeAreaId() {
		return officeAreaId;
	}

	public void setOfficeAreaId(Long officeAreaId) {
		this.officeAreaId = officeAreaId;
	}

	public String getOfficeBuildingName() {
		return officeBuildingName;
	}

	public void setOfficeBuildingName(String officeBuildingName) {
		this.officeBuildingName = officeBuildingName;
	}

	public String getOfficeFlatNo() {
		return officeFlatNo;
	}

	public void setOfficeFlatNo(String officeFlatNo) {
		this.officeFlatNo = officeFlatNo;
	}

	public String getResidenceRemarks() {
		return residenceRemarks;
	}

	public void setResidenceRemarks(String residenceRemarks) {
		this.residenceRemarks = residenceRemarks;
	}

	public String getOfficeRemarks() {
		return officeRemarks;
	}

	public void setOfficeRemarks(String officeRemarks) {
		this.officeRemarks = officeRemarks;
	}

	public Double getResidenceLatitude() {
		return residenceLatitude;
	}

	public void setResidenceLatitude(Double residenceLatitude) {
		this.residenceLatitude = residenceLatitude;
	}

	public Double getResidenceLongitude() {
		return residenceLongitude;
	}

	public void setResidenceLongitude(Double residenceLongitude) {
		this.residenceLongitude = residenceLongitude;
	}

	public Double getOfficeLatitude() {
		return officeLatitude;
	}

	public void setOfficeLatitude(Double officeLatitude) {
		this.officeLatitude = officeLatitude;
	}

	public Double getOfficeLongitude() {
		return officeLongitude;
	}

	public void setOfficeLongitude(Double officeLongitude) {
		this.officeLongitude = officeLongitude;
	}

}
