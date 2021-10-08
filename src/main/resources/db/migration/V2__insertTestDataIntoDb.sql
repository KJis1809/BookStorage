INSERT INTO books (book_id, name, release_date, isbn_code) VALUES (1, 'Thinking in Java', '1998-01-01', '1-2345-2311');
INSERT INTO books (book_id, name, release_date, isbn_code) VALUES (2, 'Clean Code', '2008-08-01', '1-4213-1342');
INSERT INTO books (book_id, name, release_date, isbn_code) VALUES (3, 'The Clean Coder', '2011-08-01', '1-9235-1234');
INSERT INTO books (book_id, name, release_date, isbn_code) VALUES (4, 'Data Structures and Algorithms', '2002-11-06', '2-2317-8679');

INSERT INTO authors (author_id, first_name, last_name, pseudonym, dob) VALUES (1, 'Bruce', 'Eckel', 'Bruce001', '1957-07-08');
INSERT INTO authors (author_id, first_name, last_name, pseudonym, dob) VALUES (2, 'Robert', 'Martin', 'UncleBob', '1952-12-05');
INSERT INTO authors (author_id, first_name, last_name, pseudonym, dob) VALUES (3, 'Robert', 'Lafore', 'Roberto_231', '1938-03-11');

INSERT INTO books_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO books_authors (book_id, author_id) VALUES (2, 2);
INSERT INTO books_authors (book_id, author_id) VALUES (3, 2);
INSERT INTO books_authors (book_id, author_id) VALUES (4, 3);