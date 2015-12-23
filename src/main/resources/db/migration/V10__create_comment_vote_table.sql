create table comment_vote (
    id bigserial primary key,
    comment_id bigint NOT NULL,
    user_id bigint NOT NULL,
    vote numeric DEFAULT 0 NOT NULL,
    CONSTRAINT fk1_comment_vote_comment FOREIGN KEY (comment_id)
          REFERENCES comment (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_comment_vote_user FOREIGN KEY (user_id)
              REFERENCES user_account (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into comment_vote (comment_id, user_id, vote)
VALUES(1, 1, 1);