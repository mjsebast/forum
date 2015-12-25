create table post_vote (
    id bigserial primary key,
    post_id bigint NOT NULL,
    user_id bigint NOT NULL,
    vote numeric DEFAULT 0 NOT NULL,
    CONSTRAINT fk1_post_vote_post FOREIGN KEY (post_id)
          REFERENCES post (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_post_vote_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into post_vote (post_id, user_id, vote)
VALUES(1, 1, 1);