create table thread_content (
    id bigserial primary key,
    thread_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    title varchar(100) not null,
    message varchar(1000) not null,
    CONSTRAINT fk1_thread_content_thread FOREIGN KEY (thread_id)
          REFERENCES thread (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_thread_content_language FOREIGN KEY (language_id)
              REFERENCES language (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into thread_content (thread_id, language_id, title, message)VALUES(1, 'en', 'Hello', 'Hello man!');
insert into thread_content (thread_id, language_id, title, message)VALUES(1, 'es', 'Hola', 'Hola hombre!');