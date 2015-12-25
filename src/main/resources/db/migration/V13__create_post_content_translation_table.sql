create table post_content_translation (
    id bigserial primary key,
    post_id bigint NOT NULL,
    user_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    message varchar(1000) NOT NULL,
    points numeric DEFAULT 0 NOT NULL,
    CONSTRAINT fk1_post_content_translation_post FOREIGN KEY (post_id)
          REFERENCES post (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_post_content_translation_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk3_post_content_translation_language FOREIGN KEY (language_id)
                  REFERENCES language (id) MATCH SIMPLE
                  ON UPDATE CASCADE ON DELETE CASCADE
);

insert into post_content_translation (post_id, user_id, language_id, message)VALUES(1, 2, 'es', '???');