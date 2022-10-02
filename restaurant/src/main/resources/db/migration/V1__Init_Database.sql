create table menus (
    id bigserial primary key,
    name varchar(255) not null constraint uk_menus_name unique,
    creation_at timestamp not null
);

create table menu_items (
    id bigserial primary key,
    menu_id bigint not null constraint fk_menu_items_menu_id references menus,
    name varchar(255) not null constraint uk_menu_items unique,
    price numeric(19, 2) not null
);

create table orders (
    id bigserial primary key,
    total_price numeric(19, 2) not null,
    creation_at timestamp not null
);

create table order_items (
    id bigserial primary key,
    order_id bigint not null constraint fk_order_items_order_id references orders,
    name varchar(255) not null,
    price numeric(19, 2) not null
);