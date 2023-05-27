insert into consumer(id, name, document_number, birth_date) values (-1, 'Name', '1234567890', current_date());

insert into card(id, card_type, card_number, balance, consumer_id) values (-2, 0, '1234567890', 100.0, -1);