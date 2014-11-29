insert into city (name) values ('Dubai');
insert into city (name) values ('Abu Dhabi');
insert into city (name) values ('Sharjah');
insert into city (name) values ('Al Ain');
insert into city (name) values ('Ajman');
insert into city (name) values ('Ras al-Khaimah');
insert into city (name) values ('Umm al-Quwain');
insert into city (name) values ('Khor Fakkan');
insert into city (name) values ('Dibba Al-Hisn');

INSERT INTO employee (employee_code,first_name, nationality, join_date, salary, remarks) VALUES 
('1000', 'Sharafudeen Aboobacker', 'Indian', TIMESTAMP 'now', '25000', 'Test');

INSERT INTO customer (first_name, last_name, mobile_1, mobile_2, landline, email, preference_call, preference_email, preference_sms, last_modified_date, last_modified_user) VALUES 
('Nadeer', 'Ali', '0555653982', '0555653983', '', 'coolnadz@gmail.com', 1, 0, 1, TIMESTAMP 'now', 'SYSTEM');

INSERT INTO customer_address (customer_id, area_id, building_name, flat_no, address_type, last_modified_date, last_modified_user) VALUES 
(2, 5, 'La Lune', '208', 'Residential', TIMESTAMP 'now', 'SYSTEM');

INSERT INTO customer_address (customer_id, area_id, building_name, flat_no, address_type, last_modified_date, last_modified_user) VALUES 
(2, 11, 'Building # 11', '3M Gulf', 'Office', TIMESTAMP 'now', 'SYSTEM');

INSERT INTO customer_contract(customer_id, contract_date, expiry_date, contract_no, contract_type, 
            amount, last_modified_date, last_modified_user, contract_status)
    VALUES (2, TIMESTAMP 'now', TIMESTAMP 'now', 'C/P-100001', 'Contract Type 1', '4500', TIMESTAMP 'now', 'SYSTEM', 'Active');

INSERT INTO appointment(appointment_no, appointment_date, start_date, area_id, 
				 customer_id, employee_id, remarks, last_modified_date, last_modified_user, 
				 appointment_status) 
				 VALUES ('A/P-10001', TIMESTAMP 'today', TIMESTAMP 'now', 2, 
				 2, 2, 'New appoinment by Nadeer', TIMESTAMP 'now', 'SYSTEM', 'Created');
				 
INSERT INTO appointment(appointment_no, appointment_date, start_date, area_id, 
				 customer_id, employee_id, remarks, last_modified_date, last_modified_user, 
				 appointment_status) 
				 VALUES ('A/P-10002', TIMESTAMP 'today', TIMESTAMP 'now', 2, 
				 2, 2, '3M appoinment by Nadeer', TIMESTAMP 'now', 'SYSTEM', 'Created');			 

INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_ACCOUNT_ID', 	'smppcon', 'SMPP Account Id');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_HOST', 		'localhost', 'SMPP Host IP');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_PORT', 		'2715', 'SMPP Port');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_USER_ID', 	'smppclient1', 'SMPP User Id');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_PASSWORD', 	'password', 'SMPP account password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_ACCOUNT_TYPE', 	'cp', 'Account Type');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_PREFIX_APPOINTMENT_NO', 	'JB', 'Appoinment No Prefix');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_PREFIX_EMPLOYEE_CODE', 	'EMP', 'Employee Code Prefix');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_PREFIX_CUSTOMER_CODE', 	'CUS', 'Customer Code Prefix');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_PREFIX_CUSTOMER_CONTRACT_CODE', 	'CT', 'Customer Contract Code Prefix');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_DEFAULT_PASSWORD', 		'pass123', 'Default Password');

--Email Props
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMTP_HOST', 							'mail.maxmaid.net', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_NOTIFICATION_FROM_ID', 			'noreply@maxmaid.net', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_CREATE_SUBJECT', 	'Appointment Created', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_CREATE_CONTENT', 	'Dear Customer, You appointment has been created', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_CANCEL_SUBJECT', 	'Appointment Cancelled', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_CANCEL_CONTENT', 	'Dear Customer, You appointment has been cancelled', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_APPOINTMENT_CANCEL_CONTENT', 	'Dear Customer, You appointment has been cancelled', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_APPOINTMENT_CREATE_CONTENT', 	'Dear Customer, You appointment has been created', 'Default Password');

insert into SEC_USER(email, username, first_name, last_name, password, role, enabled) values ('nadeer@infosuluzions.com', 
'nadeer', 'Nadeer', 'Ali', 'nadeer', 'ROLE_ADMIN', true);
	
insert into SEC_USER(email, username, first_name, last_name, password, role, enabled) values ('sharaf@infosuluzions.com', 
'sharaf', 'Sharafudeen', 'Aboobacker', 'sharaf', 'ROLE_ADMIN', true);

insert into SEC_USER(email, username, first_name, last_name, password, role, enabled) values ('admin', 
'Super', 'admin', 'Administrator', 'admin123', 'ROLE_ADMIN', true);

-- roles
insert into SEC_USER_ROLE(user_id, role_id) values(1,2);
insert into SEC_USER_ROLE(user_id, role_id) values(2,2);
insert into SEC_USER_ROLE(user_id, role_id) values(3,1);
insert into SEC_USER_ROLE(user_id, role_id) values(3,2);

INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_CUSTOMER_CANCELLATION_OFFSET_DAYS', '15', 'Days Offset');

INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_COMPLETE_SUBJECT', 	'Appointment Completed', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_COMPLETE_CONTENT', 	'Dear Customer, You appointment has been completed', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_APPOINTMENT_COMPLETE_CONTENT', 	'Dear Customer, You appointment has been completed', 'Default Password');

INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_UPDATE_CONTENT', 	'Dear Customer, You appointment details have been updated', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_UPDATE_SUBJECT', 	'Appointment Updated', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_APPOINTMENT_UPDATE_CONTENT', 	'Dear Customer, You appointment details have been updated', 'Default Password');


-- SMS HTTP Properties (01-Aug-2014)
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_HTTP_URL', 				'http://bulksms.vsms.net:5567/eapi/submission/send_sms/2/2.0', 'SMS Http URL');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_HTTP_USERNAME', 			'anasgh', 'SMS Http User Id');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_HTTP_PASSWORD', 			'anas2003', 'SMS Http Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_HTTP_ENCODING', 			'ISO-8859-1', 'SMS Http Encoding format');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_HTTP_DATA_STR_TEMPLATE', 	'username={1}&password={2}&message={3}&want_report=1&msisdn={4}', 'SMS Http Data String template');

UPDATE system_property set description='Add appointment SMS template', prop_value='Your appointment is scheduled on {1}. For cancellation please call 8006296. Thank you for using MaxMaid Services. www.facebook.com/maxmaidcs' 
where prop_key='PROP_SMS_APPOINTMENT_CREATE_CONTENT';

UPDATE system_property set description='Appointment completion SMS template', prop_value='Your appointment has been completed. Thank you for using MaxMaid Services. www.facebook.com/maxmaidcs' 
where prop_key='PROP_SMS_APPOINTMENT_COMPLETE_CONTENT';

-- master_setup
INSERT INTO master_setup (master_type, code, description) VALUES ('Nationality', 'UAE National','UAE National');
INSERT INTO master_setup (master_type, code, description) VALUES ('Nationality', 'Indian','Indian');
INSERT INTO master_setup (master_type, code, description) VALUES ('Nationality', 'Filipino','Filipino');
INSERT INTO master_setup (master_type, code, description) VALUES ('Nationality', 'Pakistani','Pakistani');
