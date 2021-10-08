DROP TABLE if EXISTS books CASCADE;
DROP TABLE if EXISTS authors CASCADE;
DROP TABLE if EXISTS books_authors CASCADE;

CREATE TABLE books
(
    book_id      BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name         VARCHAR(50)        NOT NULL,
    release_date DATE               NOT NULL,
    isbn_code    VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE authors
(
    author_id  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(45)        NOT NULL,
    last_name  VARCHAR(45)        NOT NULL,
    pseudonym  VARCHAR(20) UNIQUE NOT NULL,
    dob        DATE               NOT NULL
);

CREATE TABLE books_authors
(
    book_id   BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    KEY book_fk_idx (book_id),
    KEY author_fk_idx (author_id),
    CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES authors (author_id),
    CONSTRAINT book_fk FOREIGN KEY (book_id) REFERENCES books (book_id)
);