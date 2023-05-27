insert into consumer(id, name, document_number, birth_date) values (-1, 'Name', '1234567890', current_date());

insert into contact(id, mobile_phone_number, residence_phone_number, work_phone_number, email, consumer_id)
values (-2, '1234567890', '1234567890', '1234567890', 'test@test.com', -1);

insert into address(id, street, number, city, country, portal_code, consumer_id)
values (-3, 'Street', 123, 'City', 'Country', '1234567890', -1);

insert into card(id, card_type, card_number, balance, consumer_id) values (-4, 0, '1234567890', 100.0, -1);