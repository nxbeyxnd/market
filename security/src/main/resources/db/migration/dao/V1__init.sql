create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  score                 int not null,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, score)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 200),
('admin', '$2y$04$zQyMdk/tBmo241EZEt6mZuahJYw1dLLLo5fWZRAh0x4CMZVWGZDW6', 500);
--log: admin pass: 50

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);
