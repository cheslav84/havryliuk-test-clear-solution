create table users
(
    id           varchar(64) not null primary key,
    birth_date   date         not null,
    first_name   varchar(32)  not null,
    last_name    varchar(32)  not null,
    email        varchar(32)  not null,
    phone_number varchar(16),
    city         varchar(32),
    country      varchar(32),
    street       varchar(32),
    zipcode      varchar(10)
);
