create table post_content (
    id bigserial primary key,
    post_id bigint NOT NULL,
    language_id varchar(2) NOT NULL,
    title varchar(100) not null,
    message varchar(1000) not null,
    CONSTRAINT fk1_post_content_thread FOREIGN KEY (post_id)
          REFERENCES post (id) MATCH SIMPLE
          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk2_post_content_language FOREIGN KEY (language_id)
              REFERENCES language (id) MATCH SIMPLE
              ON UPDATE CASCADE ON DELETE CASCADE
);

insert into post_content (post_id, language_id, title, message)VALUES(1, 'en', 'Hello', 'Hello man!');
insert into post_content (post_id, language_id, title, message)VALUES(1, 'es', 'Hola', 'Hola hombre!');

insert into post_content (post_id, language_id, title, message)
VALUES(2, 'en', 'This is an awesome title', ' Wow, I really cant believe how awesome this title is. Can you? This is really amazing to be honest with you. Were living the dream.');
insert into post_content (post_id, language_id, title, message)
VALUES(2, 'es', 'Este es un título increíble', 'Realmente no puedo creer lo increíble este título es . ¿Puedes? Esto es realmente increíble para ser honesto con usted. Estamos viviendo el sueño.');