create table roles
(
    id   serial primary key,
    name varchar(50)
);

create table users
(
    id       bigserial primary key,
    login    varchar(20),
    password varchar(100),
    role_id  integer
        constraint user_table_role_table_id_fk
            references roles
);

create table users_orders
(
    user_id  int,
    order_id int,
    primary key (user_id, order_id),
    foreign key (user_id) references users (id),
    foreign key (order_id) references orders (id)
);

create table users_roles
(
    user_id int,
    role_id int,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create
    unique index user_table_login_uindex3
    on users (login);

insert into roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');