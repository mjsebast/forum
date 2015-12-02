create table thread_comment (
    id bigserial primary key,
    thread_id bigint NOT NULL,
    user_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    parent_id bigint NULL,
    CONSTRAINT fk1_thread_comment_thread FOREIGN KEY (thread_id)
          REFERENCES thread (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_thread_comment_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk3_thread_comment_language FOREIGN KEY (language_id)
                  REFERENCES language (id) MATCH SIMPLE
                  ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk4_thread_comment_parent FOREIGN KEY (parent_id)
                      REFERENCES thread_comment (id) MATCH SIMPLE
                      ON UPDATE CASCADE ON DELETE CASCADE
);

insert into thread_comment (thread_id, user_id, language_id)VALUES(1, 1, 'en');