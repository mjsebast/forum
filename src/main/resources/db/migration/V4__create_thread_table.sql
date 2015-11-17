create table thread (
    id bigserial primary key,
    forum_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT fk1_thread_forum FOREIGN KEY (forum_id)
          REFERENCES forum (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_thread_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into thread (forum_id, user_id)VALUES(1, 1);