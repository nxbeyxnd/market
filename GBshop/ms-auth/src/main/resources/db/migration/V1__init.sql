create table roles
(
    id   serial not null constraint rope_table_pk primary key,
    name varchar(50) not null
);

create table users
(
    id       bigserial not null constraint user_table_pk primary key,
    email    varchar(200),
    password varchar(100)
);

-- create table users_orders
-- (
--     user_id  int,
--     order_id int,
--     primary key (user_id, order_id),
--     foreign key (user_id) references users (id),
--     foreign key (order_id) references orders (id)
-- );

create table users_roles
(
    user_id bigint references users(id),
    role_id int references roles(id)
);

-- create
--     unique index user_table_login_uindex3
--     on users (login);

insert into roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');