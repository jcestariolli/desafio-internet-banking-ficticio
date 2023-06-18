insert into customer (ID, NAME, BIRTHDAY) values (1, 'Fulano de Tal', '2000-01-01');
insert into customer (ID, NAME, BIRTHDAY) values (2, 'Cicrano de Tal', '2005-12-31');
insert into customer (ID, NAME, BIRTHDAY) values (3, 'Mariazinha de Jesus', '1990-09-07');
insert into customer (ID, NAME, BIRTHDAY) values (4, 'Joãozinho Abençoado', '1995-01-04');
insert into customer (ID, NAME, BIRTHDAY) values (5, 'Xuxu Beleza', '1985-05-15');

insert into account (ID, BALANCE, EXCLUSIVE_PLAN) values ('11223344', 200.35, false);
insert into account (ID, BALANCE, EXCLUSIVE_PLAN) values ('55667788', 50.01, false);
insert into account (ID, BALANCE, EXCLUSIVE_PLAN) values ('99001122', 1000.12, true);
insert into account (ID, BALANCE, EXCLUSIVE_PLAN) values ('87654321', 500.10, false);
insert into account (ID, BALANCE, EXCLUSIVE_PLAN) values ('12345678', 100.00, true);

insert into customer_account (CUSTOMER_ID_ID, ACCOUNT_ID_ID) values (1, '11223344');
insert into customer_account (CUSTOMER_ID_ID, ACCOUNT_ID_ID) values (2, '55667788');
insert into customer_account (CUSTOMER_ID_ID, ACCOUNT_ID_ID) values (3, '99001122');
insert into customer_account (CUSTOMER_ID_ID, ACCOUNT_ID_ID) values (4, '87654321');
insert into customer_account (CUSTOMER_ID_ID, ACCOUNT_ID_ID) values (5, '12345678');
