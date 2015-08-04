create table user_account (
    id bigserial primary key,
    email varchar(255) not null,
    password varchar(255) not null
);