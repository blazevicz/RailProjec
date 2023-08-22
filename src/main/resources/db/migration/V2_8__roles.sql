insert into role (role_id, role)
values (1, 'DISPATCHER'),
       (2, 'DRIVER');
insert into dispatcher_role (dispatcher_id, role_id)
values (1, 1),
       (1, 2);
insert into driver_role (driver_id, role_id)
values (2, 2);
