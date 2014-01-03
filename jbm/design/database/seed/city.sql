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
