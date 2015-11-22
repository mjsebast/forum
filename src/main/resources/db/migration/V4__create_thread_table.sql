create table thread (
    id bigserial primary key,
    forum_id bigint NOT NULL,
    user_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    url varchar(1000) NULL,
    CONSTRAINT fk1_thread_forum FOREIGN KEY (forum_id)
          REFERENCES forum (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_thread_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk3_thread_language FOREIGN KEY (language_id)
                  REFERENCES language (id) MATCH SIMPLE
                  ON UPDATE CASCADE ON DELETE CASCADE
);

insert into thread (forum_id, user_id, language_id)VALUES(1, 1, 'en');