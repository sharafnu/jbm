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
CREATE SEQUENCE "customer_code_seq" START 1001;

ALTER TABLE customer
ALTER COLUMN id 
SET DEFAULT NEXTVAL('customer_id_seq');

ALTER TABLE customer
ALTER COLUMN customer_code 
SET DEFAULT NEXTVAL('customer_code_seq');

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
                  REFERENCES customer (id)
     , CONSTRAINT FK_appointment_2 FOREIGN KEY (employee_id)
                  REFERENCES employee (id)
     , CONSTRAINT FK_appointment_3 FOREIGN KEY (area_id)
                  REFERENCES area (id)
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
     , inovice_date TIMESTAMP
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

INSERT INTO system_property (prop_key, prop_value, description) VALUES ('TOPIC_ICONS_LOCATION', 'ui/jqxtree/images/', 'Tree nodes icon location');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('TOPIC_ICONS_EXTN', '.png', 'Tree nodes icon extenstion');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('TOPIC_CONTENT_BASE_URL', 'ui/', 'Topic content URL pattern');
INSERT INTO system_property (prop_key, prop_value, description) VALUES ('TOPIC_CONTENT_SUFFIX', '.jsp', 'Topic content page extenstion');

-- sec_user

CREATE SEQUENCE "sec_user_id_seq";

CREATE TABLE sec_user (
       id BIGINT NOT NULL
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

insert into SEC_USER(email, first_name, last_name, password, enabled) values (
	'nadeer@infosuluzions.com', 'Nadeer', 'Ali', 'nadeer', 'CUSTOMER_CARE', true);
	
insert into SEC_USER(email, first_name, last_name, password, enabled) values (
	'sharaf@infosuluzions.com', 'Sharafudeen', 'Aboobacker', 'CUSTOMER_CARE', true);

insert into SEC_USER(email, first_name, last_name, password, enabled) values (
	'admin', 'Super', 'Administrator', 'admin123', 'ADMIN', true);

	
