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

CREATE TABLE jbm.city (
       id BIGINT NOT NULL
     , name VARCHAR(128) NOT NULL
     , PRIMARY KEY (id)
);

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

CREATE TABLE jbm.area (
       id BIGINT NOT NULL
     , name VARCHAR(512) NOT NULL
     , city_id BIGINT NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_area_1 FOREIGN KEY (city_id)
                  REFERENCES jbm.city (id)
);

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

