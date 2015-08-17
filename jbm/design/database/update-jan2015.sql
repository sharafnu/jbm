ALTER TABLE customer_address ALTER COLUMN area_id DROP not null;

ALTER TABLE system_property ADD COLUMN prop_group VARCHAR(128);

UPDATE system_property SET prop_group='SMS_SMPP_PROPERTIES' where prop_key like 'PROP_SMS_SMPP%';

UPDATE system_property SET prop_group='SMS_HTTP_PROPERTIES' where prop_key like 'PROP_SMS_HTTP%';

UPDATE system_property SET prop_group='SYETEM_CODE_PREFIX' where prop_key like 'PROP_PREFIX_%';

UPDATE system_property SET prop_group='SMS_CONTENT' where prop_key like 'PROP_SMS_%';

UPDATE system_property SET prop_group='EMAIL_CONTENT' where prop_key like 'PROP_EMAIL_APPOINTMENT_%';

UPDATE system_property SET prop_group='EMAIL_POPERTIES' where prop_key in ('PROP_EMAIL_NOTIFICATION_FROM_ID', 'PROP_SMTP_HOST');

UPDATE system_property SET prop_group='APPOINTMENT_SETTINGS' where prop_key in ('PROP_CUSTOMER_CANCELLATION_OFFSET_DAYS');

INSERT INTO system_property(prop_group, prop_key, prop_value, description) values 
('APPOINTMENT_SETTINGS', 'ENABLE_APPOINTMENT_CREATION_SMS', 'No', 'Flag to enable/disable SMS sending option while creating appointment. If set as Yes, appoinment creation page will have the send sms option enabled.');

INSERT INTO system_property(prop_group, prop_key, prop_value, description) values 
('APPOINTMENT_SETTINGS', 'ENABLE_APPOINTMENT_COMPLETION_SMS', 'No', 'Flag to enable/disable SMS sending option while completing appointment. If set as Yes, appoinment completion page will have the send sms option enabled.');

INSERT INTO system_property(prop_group, prop_key, prop_value, description) values 
('APPOINTMENT_SETTINGS', 'ENABLE_APPOINTMENT_CANCELLATION_SMS', 'No', 'Flag to enable/disable SMS sending option while cancelling appointment. If set as Yes, appoinment cancellation page will have the send sms option enabled.');

select outerAppointment.start_date, 
outerAppointment.end_date, count(*) as appointmentCount, 
(SELECT  textcat_all(e.first_name || ', ')  as employee_names 
FROM appointment appointmentInner inner join employee e on e.id=appointmentInner.employee_id where appointmentInner.start_date=outerAppointment.start_date and appointmentInner.end_date=outerAppointment.end_date) from appointment outerAppointment where outerAppointment.appointment_status <> ''  group by outerAppointment.start_date, outerAppointment.end_date order by outerAppointment.start_date desc


CREATE AGGREGATE textcat_all(
  basetype    = text,
  sfunc       = textcat,
  stype       = text,
  initcond    = ''
);

-- New
ALTER table appointment alter column hours_spent type numeric;


INSERT INTO system_property(prop_group, prop_key, prop_value, description) values 
('APPOINTMENT_SETTINGS', 'ENABLE_APPOINTMENT_UPDATE_SMS', 'No', 'Flag to enable/disable SMS sending option while editing an appointment. If set as Yes, appoinment update page will have the send sms option enabled.');

--Changes for new provider
UPDATE system_property SET prop_value='user={1}&pass={2}&senderid=4799&message={3}&mobile={4}&lang=0&dlr=1' WHERE prop_key='PROP_SMS_HTTP_DATA_STR_TEMPLATE';
UPDATE system_property SET prop_value='http://synapse.vectramind.com/push.aspx' WHERE prop_key='PROP_SMS_HTTP_URL';
INSERT INTO system_property(prop_group, prop_key, prop_value, description) values 
('SMS_CONTENT', 'PROP_SMS_HTTP_PARAMETER_METHOD', 'URL_APPEND', 'SMS Parameter append method.');

