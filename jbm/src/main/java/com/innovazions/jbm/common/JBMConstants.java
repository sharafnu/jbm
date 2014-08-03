package com.innovazions.jbm.common;

public interface JBMConstants {

	// System property keys

	// Email Properties
	public static final String PROP_SMTP_HOST = "PROP_SMTP_HOST";
	public static final String PROP_EMAIL_NOTIFICATION_FROM_ID = "PROP_EMAIL_NOTIFICATION_FROM_ID";
	public static final String PROP_EMAIL_APPOINTMENT_CREATE_SUBJECT = "PROP_EMAIL_APPOINTMENT_CREATE_SUBJECT";
	public static final String PROP_EMAIL_APPOINTMENT_CREATE_CONTENT = "PROP_EMAIL_APPOINTMENT_CREATE_CONTENT";
	public static final String PROP_EMAIL_APPOINTMENT_CANCEL_SUBJECT = "PROP_EMAIL_APPOINTMENT_CANCEL_SUBJECT";
	public static final String PROP_EMAIL_APPOINTMENT_CANCEL_CONTENT = "PROP_EMAIL_APPOINTMENT_CANCEL_CONTENT";
	public static final String PROP_EMAIL_APPOINTMENT_COMPLETE_SUBJECT = "PROP_EMAIL_APPOINTMENT_COMPLETE_SUBJECT";
	public static final String PROP_EMAIL_APPOINTMENT_COMPLETE_CONTENT = "PROP_EMAIL_APPOINTMENT_COMPLETE_CONTENT";
	public static final String PROP_EMAIL_APPOINTMENT_UPDATE_SUBJECT = "PROP_EMAIL_APPOINTMENT_UPDATE_SUBJECT";
	public static final String PROP_EMAIL_APPOINTMENT_UPDATE_CONTENT = "PROP_EMAIL_APPOINTMENT_UPDATE_CONTENT";

	// SMS Properties
	public static final String PROP_SMS_SMPP_ACCOUNT_ID = "PROP_SMS_SMPP_ACCOUNT_ID";
	public static final String PROP_SMS_SMPP_HOST = "PROP_SMS_SMPP_HOST";
	public static final String PROP_SMS_SMPP_PORT = "PROP_SMS_SMPP_PORT";
	public static final String PROP_SMS_SMPP_USER_ID = "PROP_SMS_SMPP_USER_ID";
	public static final String PROP_SMS_SMPP_PASSWORD = "PROP_SMS_SMPP_PASSWORD";
	public static final String PROP_SMS_SMPP_ACCOUNT_TYPE = "PROP_SMS_SMPP_ACCOUNT_TYPE";
	public static final String PROP_SMS_APPOINTMENT_CREATE_CONTENT = "PROP_SMS_APPOINTMENT_CREATE_CONTENT";
	public static final String PROP_SMS_APPOINTMENT_CANCEL_CONTENT = "PROP_SMS_APPOINTMENT_CANCEL_CONTENT";
	public static final String PROP_SMS_APPOINTMENT_COMPLETE_CONTENT = "PROP_SMS_APPOINTMENT_COMPLETE_CONTENT";
	public static final String PROP_SMS_APPOINTMENT_UPDATE_CONTENT = "PROP_SMS_APPOINTMENT_UPDATE_CONTENT";
	
	// SMS Properties HTTP
	public static final String PROP_SMS_HTTP_URL = "PROP_SMS_HTTP_URL";//"http://bulksms.vsms.net:5567/eapi/submission/send_sms/2/2.0";
	public static final String PROP_SMS_HTTP_USERNAME = "PROP_SMS_HTTP_USERNAME";//"anasgh";
	public static final String PROP_SMS_HTTP_PASSWORD = "PROP_SMS_HTTP_PASSWORD";//"anas2003";
	public static final String PROP_SMS_HTTP_ENCODING = "PROP_SMS_HTTP_ENCODING";//"ISO-8859-1";
	public static final String PROP_SMS_HTTP_DATA_STR_TEMPLATE = "PROP_SMS_HTTP_DATA_STR_TEMPLATE";//"username={1}&password={2}&message={3}&want_report=1&msisdn={4}";	
	
	// Prefix Properties
	public static final String PROP_PREFIX_APPOINTMENT_NO = "PROP_PREFIX_APPOINTMENT_NO";
	public static final String PROP_PREFIX_EMPLOYEE_CODE = "PROP_PREFIX_EMPLOYEE_CODE";
	public static final String PROP_PREFIX_CUSTOMER_CODE = "PROP_PREFIX_CUSTOMER_CODE";
	public static final String PROP_PREFIX_CUSTOMER_CONTRACT_CODE = "PROP_PREFIX_CUSTOMER_CONTRACT_CODE";
	
	//Cancellation history offset days
	public static final String PROP_CUSTOMER_CANCELLATION_OFFSET_DAYS = "PROP_CUSTOMER_CANCELLATION_OFFSET_DAYS";
	
	//Default Password
	public static final String PROP_DEFAULT_PASSWORD = "PROP_DEFAULT_PASSWORD";
	
	public static final String LOGGED_IN_USER_NAME = "loggedInUserName";
	public static final String LOGGED_IN_USER_FULL_NAME = "loggedInUserFullName";
	public static final String LOGGED_IN_USER_OBJ = "loggedInUserObj";

	// Sequence Name
	public static final String SEQ_APPOINTMENT_NO = "appointment_no_seq";
	public static final String SEQ_EMPLOYEE_CODE = "employee_code_seq";
	public static final String SEQ_CUSTOMER_CODE = "customer_code_seq";
	public static final String SEQ_CUSTOMER_CONTRACT_CODE = "customer_contract_id_seq";
	
	// Appointment Status
	public static final String APPOINTMENT_STATUS_ALL = "All";
	public static final String APPOINTMENT_STATUS_CREATED = "Created";
	public static final String APPOINTMENT_STATUS_COMPLETED = "Completed";
	public static final String APPOINTMENT_STATUS_CANCELLED = "Cancelled";

	// No record found message
	public static final String NO_RECORDS_FOUND_MSG = "No Records Found!";

	// Address Types
	public static final String ADDRESS_TYPE_RESIDENCE = "Office";
	public static final String ADDRESS_TYPE_OFFICE = "Residence";
	
	//EmployeeStatus
	public static final String EMPLOYEE_STATUS_ACTIVE = "Active";
	public static final String EMPLOYEE_STATUS_RESIGNED = "Resigned";
	public static final String EMPLOYEE_STATUS_INACTIVE = "In-Active";
	
	public static final String CUSTOMER_CONTRACT_ACTIVE = "Active";	
	public static final String CUSTOMER_CONTRACT_EXPIRED = "Expired";
}
