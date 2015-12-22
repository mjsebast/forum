create table user_account (
    id bigserial primary key,
    username varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null
);

insert into user_account (username,email,password)
VALUES('special_guy', 'test+dude@gmail.com', 'password');

insert into user_account (username,email,password)
VALUES('microsoft_translator', 'test+otherdude@gmail.com', 'password');