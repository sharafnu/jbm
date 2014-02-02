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
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_CREATE_CONTENT', 	'pass123', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_CANCEL_SUBJECT', 	'Appointment Cancelled', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_EMAIL_APPOINTMENT_CANCEL_CONTENT', 	'Dear Customer, You appointment has been cancelled', 'Default Password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_APPOINTMENT_CANCEL_CONTENT', 	'Dear Customer, You appointment has been cancelled', 'Default Password');

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
