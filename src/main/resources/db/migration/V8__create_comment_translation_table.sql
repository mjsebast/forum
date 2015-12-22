create table comment_translation (
    id bigserial primary key,
    comment_id bigint NOT NULL,
    user_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    message varchar(1000) NOT NULL,
    points numeric DEFAULT 0 NOT NULL,
    CONSTRAINT fk1_comment_translation_comment FOREIGN KEY (comment_id)
          REFERENCES comment (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_comment_translation_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk3_comment_translation_language FOREIGN KEY (language_id)
                  REFERENCES language (id) MATCH SIMPLE
                  ON UPDATE CASCADE ON DELETE CASCADE
);

insert into comment_translation (comment_id, user_id, language_id, message, points)VALUES(1, 1, 'es', 'Wow, es loco.', 1);
insert into comment_translation (comment_id, user_id, language_id, message)VALUES(1, 1, 'es', 'Es loco.');
insert into comment_translation (comment_id, user_id, language_id, message)VALUES(1, 2, 'es', 'Tu es loco.');