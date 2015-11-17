create table comment (
    id bigserial primary key,
    thread_id bigint NOT NULL,
    user_id bigint NOT NULL,
    comment varchar(1000) not null,
    CONSTRAINT fk1_comment_thread FOREIGN KEY (thread_id)
          REFERENCES thread (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_comment_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);