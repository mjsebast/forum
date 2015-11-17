create table language (
    id varchar(2) primary key,
    label varchar(255) not null
);

insert into language (id, label)VALUES('en', 'English');
insert into language (id, label)VALUES('es', 'Español');