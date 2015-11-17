create table forum (
    id bigserial primary key,
    name varchar(100) not null,
    description varchar(1000) not null,
    created_by bigint not null,
    CONSTRAINT fk1_forum_user FOREIGN KEY (created_by)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into forum (name,description,created_by)
VALUES('General', 'General Discussion', 1);

insert into forum (name,description,created_by)
VALUES('Pictures', 'A place to post pictures', 1);