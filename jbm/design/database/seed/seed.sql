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
('1000', 'Sharafudeen Aboobacker', 'Indian', TIMESTAMP 'now', '25000', 'Test')

INSERT INTO customer (first_name, last_name, mobile_1, mobile_2, mobile_3, email, preference_call, preference_email, preference_sms, last_modified_date, last_modified_user) VALUES 
('Nadeer', 'Ali', '0555653982', '0555653983', '', 'coolnadz@gmail.com', 1, 0, 1, TIMESTAMP 'now', 'SYSTEM');

SELECT currval('customer_id_seq')

select ca.id as address_id, ca.customer_id as customer_id, ca.area_id as area_id, 
a.name as area_name, c.name as city_name, ca.building_name as building_name, 
ca.flat_no as flat_no, ca.address_type as address_type, ca.last_modified_date as last_modified_date, 
ca.last_modified_user as last_modified_user from customer_address ca 
inner join area a on a.id=ca.area_id 
inner join city c on c.id=a.city_id 
where ca.customer_id=?

INSERT INTO customer_address (customer_id, area_id, building_name, flat_no, address_type, last_modified_date, last_modified_user) VALUES 
(2, 5, 'La Lune', '208', 'Residential', TIMESTAMP 'now', 'SYSTEM');

INSERT INTO customer_address (customer_id, area_id, building_name, flat_no, address_type, last_modified_date, last_modified_user) VALUES 
(2, 11, 'Building # 11', '3M Gulf', 'Office', TIMESTAMP 'now', 'SYSTEM');

INSERT INTO customer_contract(customer_id, contract_date, expiry_date, contract_no, contract_type, 
            amount, last_modified_date, last_modified_user, contract_status)
    VALUES (2, TIMESTAMP 'now', TIMESTAMP 'now', 'C/P-100001', 'Contract Type 1', '4500', TIMESTAMP 'now', 'SYSTEM', 'Active');

    
SELECT 
	a.id as appoinment_id, a.appointment_no as appointment_no, a.appointment_date as appointment_date, 
	a.start_date as start_date, a.end_date as end_date, a.area_id as area_id, 
	a.customer_id as customer_id, a.employee_id as employee_id, a.remarks as remarks, 
	a.hours_spent as hours_spent, a.payable_amount as payable_amount, 
	a.payment_status as payment_status, a.last_modified_date as last_modified_date, 
	a.last_modified_user as last_modified_user, a.appointment_status as appointment_status, 
	a.cancellation_reason as cancellation_reason, 
	c.first_name as customer_first_name, c.last_name as customer_last_name, 
	e.first_name as employee_first_name,
	ar.name as area_name, 
	ci.name as city_name 
  FROM appointment a 
  inner join customer c on c.id=a.customer_id 
  inner join employee e on e.id=a.employee_id 
  inner join area ar on ar.id=a.area_id 
  inner join city ci on ci.id=ar.city_id;

  

INSERT INTO appointment(appointment_no, appointment_date, start_date, area_id, 
				 customer_id, employee_id, remarks, last_modified_date, last_modified_user, 
				 appointment_status) 
				 VALUES ('A/P-10001', TIMESTAMP 'today', TIMESTAMP 'now', 2, 
				 2, 2, 'New appoinment by Nadeer', TIMESTAMP 'now', 'SYSTEM', 'Created')
				 
INSERT INTO appointment(appointment_no, appointment_date, start_date, area_id, 
				 customer_id, employee_id, remarks, last_modified_date, last_modified_user, 
				 appointment_status) 
				 VALUES ('A/P-10002', TIMESTAMP 'today', TIMESTAMP 'now', 2, 
				 2, 2, '3M appoinment by Nadeer', TIMESTAMP 'now', 'SYSTEM', 'Created')				 

INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_ACCOUNT_ID', 	'smppcon', 'SMPP Account Id');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_HOST', 		'localhost', 'SMPP Host IP');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_PORT', 		'2715', 'SMPP Port');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_USER_ID', 	'smppclient1', 'SMPP User Id');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_PASSWORD', 	'password', 'SMPP account password');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('PROP_SMS_SMPP_ACCOUNT_TYPE', 	'cp', 'Account Type');


insert into SEC_USER(email, first_name, last_name, password, role, enabled) values (
	'nadeer@infosuluzions.com', 'Nadeer', 'Ali', 'nadeer', 'CUSTOMER_CARE', true);
	
insert into SEC_USER(email, first_name, last_name, password, role, enabled) values (
	'sharaf@infosuluzions.com', 'Sharafudeen', 'Aboobacker', 'sharaf', 'CUSTOMER_CARE', true);

insert into SEC_USER(email, first_name, last_name, password, role, enabled) values (
	'admin', 'Super', 'Administrator', 'admin123', 'ADMIN', true);


	