create table orders
(
    id            bigserial primary key,
    product_id    int references products (id) ON DELETE CASCADE,
    title         VARCHAR(255),
    count         int,
    cost_per_item int,
    cost          int
);

CREATE TYPE delivery_status AS ENUM ('DELIVERED', 'CANCELED', 'IN_PROCESS', 'STARTING');
create table delivery
(
    delivery_status_id serial primary key,
    status             delivery_status
);

CREATE TYPE payment_status AS ENUM ('SUCCESS', 'ERROR');
create table payment
(
    payment_status_id serial primary key,
    status            payment_status
);

create table delivery_orders
(
    order_id        integer references orders (id),
    delivery_status integer references delivery (delivery_status_id),
    primary key (order_id, delivery_status),
    foreign key (order_id) references orders (id),
    foreign key (delivery_status) references delivery (delivery_status_id)
);

CREATE table payment_orders
(
    order_id       int,
    payment_status int,
    primary key (order_id, payment_status),
    foreign key (order_id) references orders (id),
    foreign key (payment_status) references payment (payment_status_id)
);