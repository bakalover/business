insert into roles values(1,'ROLE_USER');
insert into roles values(2,'ROLE_MODERATOR');
insert into roles values(3,'ROLE_ADMIN');
insert into user_dao values(false, 'admin1');
insert into user_dao values(false,'moderator');
insert into users_roles values(3, 'admin1');
insert into users_roles values(2,'moderator');