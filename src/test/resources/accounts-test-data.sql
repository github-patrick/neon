insert into Manager (id, username, password, first_name, last_name, created_at, updated_at)
values (1, 'ugwupa', 'password', 'patrick', 'ugwu', NOW(), NOW());

insert into Customer (id, username, password, first_name, last_name, manager_id,created_at, updated_at)
values (1, 'John', 'password', 'Jupiter', 'Raul', 1, NOW(), NOW());

insert into Customer (id, username, password, first_name, last_name, manager_id,created_at, updated_at)
values (2, 'Barry', 'password', 'Jupiter', 'Raul', 1, NOW(), NOW());

insert into Account (id, balance, blocked, customer_id, created_at, updated_at)
values (1, 10.00, false, 1, NOW(), NOW());

insert into Account (id, balance, blocked, customer_id, created_at, updated_at)
values (2, 20.00, false, 1, NOW(), NOW());

insert into Account (id, balance, blocked, customer_id, created_at, updated_at)
values (3, 30.00, false, 2, NOW(), NOW());

insert into Account (id, balance, blocked, customer_id, created_at, updated_at)
values (4, 40.00, false, 2, NOW(), NOW());