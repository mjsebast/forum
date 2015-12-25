create table post_title_translation_vote (
    id bigserial primary key,
    post_title_translation_id bigint NOT NULL,
    user_id bigint NOT NULL,
    vote numeric DEFAULT 0 NOT NULL,
    CONSTRAINT fk1_post_title_translation_vote_post_title_translation FOREIGN KEY (post_title_translation_id)
          REFERENCES post_title_translation (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_post_title_translation_vote_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);