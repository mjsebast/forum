create table comment (
    id bigserial primary key,
    post_id bigint NOT NULL,
    user_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    parent_id bigint NULL,
    points numeric DEFAULT 0 NOT NULL,
    CONSTRAINT fk1_comment_post FOREIGN KEY (post_id)
          REFERENCES post (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_comment_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk3_comment_language FOREIGN KEY (language_id)
                  REFERENCES language (id) MATCH SIMPLE
                  ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk4_comment_parent FOREIGN KEY (parent_id)
                      REFERENCES comment (id) MATCH SIMPLE
                      ON UPDATE CASCADE ON DELETE CASCADE
);

insert into comment (post_id, user_id, language_id)VALUES(1, 1, 'en');