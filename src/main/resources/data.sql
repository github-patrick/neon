insert into Manager (id, username, password, first_name, last_name, created_at, updated_at)
values (1, 'ugwupa', 'password', 'patrick', 'ugwu', NOW(), NOW());

insert into Manager (id, username, password, first_name, last_name, created_at, updated_at)
values (2, 'ckipper', 'password', 'chris', 'kipper', NOW(), NOW());

insert into Customer (id, username, password, first_name, last_name, manager_id,created_at, updated_at)
values (1, 'John', 'password', 'Jupiter', 'Raul', 1, NOW(), NOW());

insert into Customer (id, username, password, first_name, last_name, manager_id,created_at, updated_at)
values (2, 'Peter', 'password', 'Mars', 'Money', 1, NOW(), NOW());

insert into Customer (id, username, password, first_name, last_name, manager_id,created_at, updated_at)
values (3, 'Paul', 'password', 'Earth', 'Roper', 1, NOW(), NOW());

insert into Customer (id, username, password, first_name, last_name, manager_id,created_at, updated_at)
values (4, 'Luke', 'password', 'Pluto', 'Jacobs', 2, NOW(), NOW());