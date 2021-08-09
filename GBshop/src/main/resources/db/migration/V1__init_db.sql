create table products
(
    id         bigserial primary key,
    title      VARCHAR(255),
    cost       int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users
(
    id       bigserial primary key,
    login    varchar(20) unique,
    password varchar(100),
    email    varchar(255) unique
);

create table orders
(
    id            bigserial primary key,
    product_id    int references products (id) ON DELETE CASCADE,
    title         VARCHAR(255),
    count         int,
    cost_per_item int,
    cost          int
);

create table users_orders
(
    user_id  int,
    order_id int,
    primary key (user_id, order_id),
    foreign key (user_id) references users (id),
    foreign key (order_id) references orders (id)
);

create table roles
(
    id   serial primary key,
    name varchar(50)
);

create table users_roles
(
    user_id int,
    role_id int,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table stock
(
    id  int primary key,
    stock_count int
);

create table stock_products(
    product_id int,
    stock_id int,
    primary key (product_id, stock_id),
    foreign key (product_id) references products(id),
    foreign key (stock_id) references stock(id)
);

CREATE TYPE delivery_status AS ENUM ('DELIVERED', 'CANCELED', 'IN_PROCESS', 'STARTING');
create table delivery
(
    delivery_status_id serial primary key,
    status             delivery_status
);

create table delivery_orders
(
    order_id        integer references orders (id),
    delivery_status integer references delivery (delivery_status_id),
    primary key (order_id, delivery_status),
    foreign key (order_id) references orders (id),
    foreign key (delivery_status) references delivery (delivery_status_id)
);

CREATE TYPE payment_status AS ENUM ('SUCCESS', 'ERROR');
create table payment
(
    payment_status_id serial primary key,
    status            payment_status
);

CREATE table payment_orders
(
    order_id       int,
    payment_status int,
    primary key (order_id, payment_status),
    foreign key (order_id) references orders (id),
    foreign key (payment_status) references payment (payment_status_id)
);




insert into products (title, cost)
VALUES ('potatoe', 100),
       ('carrot', 120),
       ('tomato', 300),
       ('corn', 400),
       ('juice', 1500),
       ('milk', 1100),
       ('meat', 3000),
       ('ketchup', 1700),
       ('cookie', 7100),
       ('cookie2', 7200),
       ('cookie3', 7030),
       ('cookie4', 7004),
       ('cookie5', 7600),
       ('cookie6', 7070),
       ('cookie7', 7008),
       ('cookie8', 9700),
       ('cookie9', 71200),
       ('cookie10', 71400),
       ('cookie11', 7050),
       ('cookie11', 7060),
       ('cookie12', 7800),
       ('cookie13', 78500),
       ('cookie14', 701650),
       ('cookie15', 7060),
       ('cookie16', 70260),
       ('cookie17', 70270),
       ('cookie18', 70270),
       ('cookie19', 70280),
       ('cookie20', 7100),
       ('cookie21', 5700),
       ('cookie22', 6700),
       ('cookie23', 8700),
       ('cookie24', 7100),
       ('cookie25', 99700),
       ('cookie26', 89700),
       ('cookie27', 78600),
       ('cookie28', 7047450),
       ('cookie29', 70470),
       ('cookie30', 70470),
       ('cookie31', 70748740),
       ('cookie32', 704480);

insert into roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (login, password, email)
VALUES ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);

insert into stock(id, stock_count)
values (1, 100),
       (2, 200),
       (3, 500),
       (4, 5151);

insert into stock_products(product_id, stock_id)
values (1,1),
       (2,2),
       (3,3),
       (4,4);