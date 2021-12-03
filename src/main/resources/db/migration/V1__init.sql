create table products
(
    id    integer not null
        constraint products_pk
            primary key autoincrement,
    title text    not null,
    cost  integer not null
);