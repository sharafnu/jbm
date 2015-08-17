<<<<<<< HEAD
-- employee
CREATE TABLE employee (
       id BIGINT NOT NULL
     , employee_code VARCHAR(16)
     , first_name VARCHAR(512)
     , nationality VARCHAR(128)
     , join_date TIMESTAMP
     , salary DOUBLE PRECISION
     , remarks VARCHAR(1024)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , PRIMARY KEY (id)
);

CREATE SEQUENCE "employee_id_seq";

ALTER TABLE employee
ALTER COLUMN id 
SET DEFAULT NEXTVAL('employee_id_seq');

-- city
CREATE TABLE city (
       id BIGINT NOT NULL
     , name VARCHAR(128) NOT NULL
     , PRIMARY KEY (id)
);

CREATE SEQUENCE "city_id_seq";

ALTER TABLE city
ALTER COLUMN id 
SET DEFAULT NEXTVAL('city_id_seq');

-- customer
CREATE TABLE customer (
       id BIGINT NOT NULL
     , customer_code VARCHAR(16) NOT NULL
     , first_name VARCHAR(512) NOT NULL
     , last_name VARCHAR(512)
     , mobile_1 VARCHAR(16) NOT NULL
     , mobile_2 VARCHAR(16)
     , mobile_3 VARCHAR(16)
     , email VARCHAR(128) NOT NULL
     , preference_call INT NOT NULL
     , preference_email INT NOT NULL
     , preference_sms INT NOT NULL
     , last_modified_date TIMESTAMP NOT NULL
     , last_modified_user VARCHAR(16) NOT NULL
     , PRIMARY KEY (id)
);

CREATE SEQUENCE "customer_id_seq";

ALTER TABLE customer
ALTER COLUMN id 
SET DEFAULT NEXTVAL('customer_id_seq');

--ALTER TABLE customer
--ALTER COLUMN customer_code 
--SET DEFAULT NEXTVAL('customer_code_seq');

ALTER TABLE ONLY customer ALTER COLUMN customer_code SET DEFAULT NULL;

-- area
CREATE TABLE area (
       id BIGINT NOT NULL
     , name VARCHAR(512) NOT NULL
     , city_id BIGINT NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_area_1 FOREIGN KEY (city_id)
                  REFERENCES city (id)
);

CREATE SEQUENCE "area_id_seq";

ALTER TABLE area
ALTER COLUMN id 
SET DEFAULT NEXTVAL('area_id_seq');

-- appointment
CREATE TABLE appointment (
       id BIGINT NOT NULL
     , appointment_no VARCHAR(64) NOT NULL
     , appointment_date TIMESTAMP NOT NULL
     , start_date TIMESTAMP
     , end_date TIMESTAMP
     , customer_address_id BIGINT
     , customer_id BIGINT NOT NULL
     , employee_id BIGINT
     , remarks VARCHAR(1024)
     , hours_spent INT
     , payable_amount DOUBLE PRECISION
     , payment_status VARCHAR(16)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16) NOT NULL
     , appointment_status VARCHAR(16)
     , cancellation_reason VARCHAR(1024)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_appointment_1 FOREIGN KEY (customer_id)
                  REFERENCES customer (id)
     , CONSTRAINT FK_appointment_2 FOREIGN KEY (employee_id)
                  REFERENCES employee (id)
     , CONSTRAINT FK_appointment_3 FOREIGN KEY (customer_address_id)
                  REFERENCES customer_address (id)
);

CREATE SEQUENCE "appointment_id_seq";

ALTER TABLE appointment
ALTER COLUMN id 
SET DEFAULT NEXTVAL('appointment_id_seq');

-- appointment_payment
CREATE TABLE appointment_payment (
       id BIGINT NOT NULL
     , appointment_id BIGINT NOT NULL
     , amount_paid DOUBLE PRECISION NOT NULL
     , payment_mode VARCHAR(16)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_appointment_payment_1 FOREIGN KEY (appointment_id)
                  REFERENCES appointment (id)
);

CREATE SEQUENCE "appointment_payment_id_seq";

ALTER TABLE appointment_payment
ALTER COLUMN id 
SET DEFAULT NEXTVAL('appointment_payment_id_seq');

-- customer_contract
CREATE TABLE customer_contract (
       id BIGINT NOT NULL
     , customer_id BIGINT NOT NULL
     , contract_date TIMESTAMP
     , expiry_date TIMESTAMP
     , contract_no VARCHAR(256)
     , contract_type VARCHAR(16)
     , amount DOUBLE PRECISION
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , contract_status VARCHAR(16)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_customer_contract_1 FOREIGN KEY (customer_id)
                  REFERENCES customer (id)
);

CREATE SEQUENCE "customer_contract_id_seq";

ALTER TABLE customer_contract
ALTER COLUMN id 
SET DEFAULT NEXTVAL('customer_contract_id_seq');

-- invoice
CREATE TABLE invoice (
       id BIGINT NOT NULL
     , invoice_no VARCHAR(256) NOT NULL
     , invoice_date TIMESTAMP
     , amount DOUBLE PRECISION
     , appointment_id BIGINT
     , status VARCHAR(16)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_invoice_2 FOREIGN KEY (appointment_id)
                  REFERENCES appointment (id)
);

CREATE SEQUENCE "invoice_id_seq";

ALTER TABLE invoice
ALTER COLUMN id 
SET DEFAULT NEXTVAL('invoice_id_seq');

-- customer_address
CREATE TABLE customer_address (
       id BIGINT NOT NULL
     , customer_id BIGINT NOT NULL
     , area_id BIGINT NOT NULL
     , building_name VARCHAR(512)
     , flat_no VARCHAR(64)
     , address_type VARCHAR(32) NOT NULL
     , last_modified_date TIMESTAMP NOT NULL
     , last_modified_user VARCHAR(16) NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_customer_address_1 FOREIGN KEY (customer_id)
                  REFERENCES customer (id)
     , CONSTRAINT FK_customer_address_2 FOREIGN KEY (area_id)
                  REFERENCES area (id)
);

CREATE SEQUENCE "customer_address_id_seq";

ALTER TABLE customer_address
ALTER COLUMN id 
SET DEFAULT NEXTVAL('customer_address_id_seq');

ALTER table customer_address 
ADD remarks varchar(512);

--system_property
CREATE TABLE system_property (
       id BIGINT NOT NULL
     , prop_key VARCHAR(512) NOT NULL
     , prop_value VARCHAR(1024) NOT NULL
     , description VARCHAR(512) NOT NULL
     , PRIMARY KEY (id)
);

CREATE SEQUENCE "system_property_id_seq";

ALTER TABLE system_property
ALTER COLUMN id 
SET DEFAULT NEXTVAL('system_property_id_seq');

-- sec_user

CREATE SEQUENCE "sec_user_id_seq";

CREATE TABLE sec_user (
       id BIGINT NOT NULL
     , username VARCHAR(32) NOT NULL  
     , email VARCHAR(128) NOT NULL
     , first_name VARCHAR(256) NOT NULL
     , last_name VARCHAR(256)
     , password VARCHAR(64) NOT NULL
     , role VARCHAR(64) NOT NULL
     , enabled BOOLEAN NOT NULL DEFAULT false
     , PRIMARY KEY (id)
);

ALTER TABLE sec_user
ALTER COLUMN id 
SET DEFAULT NEXTVAL('sec_user_id_seq');

-- sec_user_role
CREATE SEQUENCE "sec_user_role_id_seq";

CREATE TABLE sec_user_role (
       id BIGINT NOT NULL
     , user_id BIGINT NOT NULL
     , role_id BIGINT NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_user_role_2 FOREIGN KEY (user_id)
                  REFERENCES SEC_USER (id)
);

ALTER TABLE sec_user_role
ALTER COLUMN id 
SET DEFAULT NEXTVAL('sec_user_role_id_seq');

-- auto gen sequences
CREATE SEQUENCE "appointment_no_seq" START 1001;
CREATE SEQUENCE "employee_code_seq" START 1001;
CREATE SEQUENCE "customer_code_seq" START 1001;
ALTER SEQUENCE "customer_contract_id_seq" START 1001;
-- employee alter

ALTER table employee add COLUMN contact_mobile_no varchar(16);
ALTER table employee add COLUMN home_cntry_contact_no varchar(16);
ALTER table employee add COLUMN address varchar(1024);
ALTER table employee add COLUMN passport_no varchar(32);
ALTER table employee add COLUMN visa_details varchar(512);
ALTER table employee add COLUMN employee_status varchar(16);

-- customer_contract alter
alter table customer_contract add column visit_count int;
-- rename mobile_3 to landline
alter table customer rename column mobile_3 to landline;

ALTER TABLE system_property ADD CONSTRAINT UK_system_property UNIQUE (prop_key);

alter table customer_address add latitude numeric(16,10) default 25.197139;
alter table customer_address add longitude numeric(16,10) default 55.274111;

ALTER TABLE customer_address ALTER COLUMN area_id DROP NOT NULL;


-- sec_master_setup
CREATE SEQUENCE "sec_master_setup_id_seq";

CREATE TABLE master_setup (
       id BIGINT NOT NULL
     , master_type VARCHAR(32) NOT NULL
     , code VARCHAR(32) NOT NULL
     , description VARCHAR(256)
     , PRIMARY KEY (id)
);

ALTER TABLE master_setup
ALTER COLUMN id 
SET DEFAULT NEXTVAL('sec_master_setup_id_seq');

ALTER TABLE customer_address ALTER COLUMN area_id DROP not null;
=======
-- employee
CREATE TABLE jbm.employee (
       id BIGINT NOT NULL
     , employee_code VARCHAR(16)
     , first_name VARCHAR(512)
     , nationality VARCHAR(128)
     , join_date TIMESTAMP
     , salary DOUBLE PRECISION
     , remarks VARCHAR(1024)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , PRIMARY KEY (id)
);

CREATE SEQUENCE "employee_id_seq";

ALTER TABLE employee
ALTER COLUMN id 
SET DEFAULT NEXTVAL('employee_id_seq');

-- city
CREATE TABLE jbm.city (
       id BIGINT NOT NULL
     , name VARCHAR(128) NOT NULL
     , PRIMARY KEY (id)
);

CREATE SEQUENCE "city_id_seq";

ALTER TABLE city
ALTER COLUMN id 
SET DEFAULT NEXTVAL('city_id_seq');

-- customer
CREATE TABLE jbm.customer (
       id BIGINT NOT NULL
     , customer_code VARCHAR(16) NOT NULL
     , first_name VARCHAR(512) NOT NULL
     , last_name VARCHAR(512)
     , mobile_1 VARCHAR(16) NOT NULL
     , mobile_2 VARCHAR(16)
     , mobile_3 VARCHAR(16)
     , email VARCHAR(128) NOT NULL
     , preference_call INT NOT NULL
     , preference_email INT NOT NULL
     , preference_sms INT NOT NULL
     , last_modified_date TIMESTAMP NOT NULL
     , last_modified_user VARCHAR(16) NOT NULL
     , PRIMARY KEY (id)
);

CREATE SEQUENCE "customer_id_seq";

ALTER TABLE customer
ALTER COLUMN id 
SET DEFAULT NEXTVAL('customer_id_seq');

-- area
CREATE TABLE jbm.area (
       id BIGINT NOT NULL
     , name VARCHAR(512) NOT NULL
     , city_id BIGINT NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_area_1 FOREIGN KEY (city_id)
                  REFERENCES jbm.city (id)
);

CREATE SEQUENCE "area_id_seq";

ALTER TABLE area
ALTER COLUMN id 
SET DEFAULT NEXTVAL('area_id_seq');

-- appointment
CREATE TABLE jbm.appointment (
       id BIGINT NOT NULL
     , appointment_no VARCHAR(64) NOT NULL
     , appointment_date TIMESTAMP NOT NULL
     , start_date TIMESTAMP
     , end_date TIMESTAMP
     , area_id BIGINT
     , customer_id BIGINT NOT NULL
     , employee_id BIGINT
     , remarks VARCHAR(1024)
     , hours_spent INT
     , payable_amount DOUBLE PRECISION
     , payment_status VARCHAR(16)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16) NOT NULL
     , appointment_status VARCHAR(16)
     , cancellation_reason VARCHAR(1024)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_appointment_1 FOREIGN KEY (customer_id)
                  REFERENCES jbm.customer (id)
     , CONSTRAINT FK_appointment_2 FOREIGN KEY (employee_id)
                  REFERENCES jbm.employee (id)
     , CONSTRAINT FK_appointment_3 FOREIGN KEY (area_id)
                  REFERENCES jbm.area (id)
);

CREATE SEQUENCE "appointment_id_seq";

ALTER TABLE appointment
ALTER COLUMN id 
SET DEFAULT NEXTVAL('appointment_id_seq');

-- appointment_payment
CREATE TABLE jbm.appointment_payment (
       id BIGINT NOT NULL
     , appointment_id BIGINT NOT NULL
     , amount_paid DOUBLE PRECISION NOT NULL
     , payment_mode VARCHAR(16)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_appointment_payment_1 FOREIGN KEY (appointment_id)
                  REFERENCES jbm.appointment (id)
);

CREATE SEQUENCE "appointment_payment_id_seq";

ALTER TABLE appointment_payment
ALTER COLUMN id 
SET DEFAULT NEXTVAL('appointment_payment_id_seq');

-- customer_contract
CREATE TABLE jbm.customer_contract (
       id BIGINT NOT NULL
     , customer_id BIGINT NOT NULL
     , contract_date TIMESTAMP
     , expiry_date TIMESTAMP
     , contract_no VARCHAR(256)
     , contract_type VARCHAR(16)
     , amount DOUBLE PRECISION
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , contract_status VARCHAR(16)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_customer_contract_1 FOREIGN KEY (customer_id)
                  REFERENCES jbm.customer (id)
);

CREATE SEQUENCE "customer_contract_id_seq";

ALTER TABLE customer_contract
ALTER COLUMN id 
SET DEFAULT NEXTVAL('customer_contract_id_seq');

-- invoice
CREATE TABLE jbm.invoice (
       id BIGINT NOT NULL
     , invoice_no VARCHAR(256) NOT NULL
     , inovice_date TIMESTAMP
     , amount DOUBLE PRECISION
     , appointment_id BIGINT
     , status VARCHAR(16)
     , last_modified_date TIMESTAMP
     , last_modified_user VARCHAR(16)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_invoice_2 FOREIGN KEY (appointment_id)
                  REFERENCES jbm.appointment (id)
);

CREATE SEQUENCE "invoice_id_seq";

ALTER TABLE invoice
ALTER COLUMN id 
SET DEFAULT NEXTVAL('invoice_id_seq');

-- customer_address
CREATE TABLE jbm.customer_address (
       id BIGINT NOT NULL
     , customer_id BIGINT NOT NULL
     , area_id BIGINT NOT NULL
     , building_name VARCHAR(512)
     , flat_no VARCHAR(64)
     , address_type VARCHAR(32) NOT NULL
     , last_modified_date TIMESTAMP NOT NULL
     , last_modified_user VARCHAR(16) NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_customer_address_1 FOREIGN KEY (customer_id)
                  REFERENCES jbm.customer (id)
     , CONSTRAINT FK_customer_address_2 FOREIGN KEY (area_id)
                  REFERENCES jbm.area (id)
);

CREATE SEQUENCE "customer_address_id_seq";

ALTER TABLE customer_address
ALTER COLUMN id 
SET DEFAULT NEXTVAL('customer_address_id_seq');

>>>>>>> refs/remotes/origin/master
