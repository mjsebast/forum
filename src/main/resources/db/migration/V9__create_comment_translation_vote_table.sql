create table comment_translation_vote (
    id bigserial primary key,
    comment_translation_id bigint NOT NULL,
    user_id bigint NOT NULL,
    vote numeric DEFAULT 0 NOT NULL,
    CONSTRAINT fk1_comment_translation_vote_translation FOREIGN KEY (comment_translation_id)
          REFERENCES comment_translation (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_comment_translation_vote_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into comment_translation_vote (comment_translation_id, user_id, vote)
VALUES(1, 1, 1);