insert into customer (NAME, BIRTHDAY) values ('Fulano de Tal', '2000-01-01');
insert into customer (NAME, BIRTHDAY) values ('Cicrano de Tal', '2005-12-31');
insert into customer (NAME, BIRTHDAY) values ('Mariazinha de Jesus', '1990-09-07');
insert into customer (NAME, BIRTHDAY) values ('Joãozinho Abençoado', '1995-01-04');
insert into customer (NAME, BIRTHDAY) values ('Xuxu Beleza', '1985-05-15');

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

insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('11223344','deposito', 200.35,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('11223344','saque', 20,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('55667788','deposito', 100,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('55667788','saque', 50,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('99001122','deposito', 100,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('99001122','saque', 90,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('87654321','deposito', 5500,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('87654321','saque', 1000,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('12345678','deposito', 55.05,  CURRENT_DATE());
insert into transaction (ACCOUNT_ID, COMMAND, AMMOUNT, EXECUTED_ON) values ('12345678','saque', 55.05,  CURRENT_DATE());

