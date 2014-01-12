SELECT currval('customer_id_seq')

select ca.id as address_id, ca.customer_id as customer_id, ca.area_id as area_id, 
a.name as area_name, c.name as city_name, ca.building_name as building_name, 
ca.flat_no as flat_no, ca.address_type as address_type, ca.last_modified_date as last_modified_date, 
ca.last_modified_user as last_modified_user from customer_address ca 
inner join area a on a.id=ca.area_id 
inner join city c on c.id=a.city_id 
where ca.customer_id=?

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
