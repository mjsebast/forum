create table comment_content (
    id bigserial primary key,
    comment_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    message varchar(1000) not null,
    CONSTRAINT fk1_comment_content_comment FOREIGN KEY (comment_id)
          REFERENCES comment (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_comment_content_language FOREIGN KEY (language_id)
              REFERENCES language (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into comment_content (comment_id, language_id, message)VALUES(1, 'en', 'Wow, thats crazy.');