--Default User
INSERT INTO _user (id, first_name, last_name, email, password, role) VALUES (1, 'Manuel', 'Sánchez', 'manuel@57blocks.com', '$2a$10$o0BsgO6E0DO369xeIt6fieakXJD.XzBDNC9AV4eqlMZKy7Mc69R86', 'ADMIN');
INSERT INTO _user (id, first_name, last_name, email, password, role) VALUES (2, 'Fernando', 'González', 'fernando@57blocks.com', '$2a$10$o0BsgO6E0DO369xeIt6fieakXJD.XzBDNC9AV4eqlMZKy7Mc69R86', 'USER');

--Public Songs
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (1, 'Experiencias Vividas', 'Diomedes Díaz', 'Vallenato', '2006', 'Colombia', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (2, 'Fiesta Pagana', 'Mago de Oz', 'Folk Metal', '2000', 'Spain', true, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (3, 'El Cantante', 'Héctor Lavoe', 'Salsa', '1978', 'Puerto Rico', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (4, 'Idilio', 'Willie Colón', 'Salsa', '2000', 'United States', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (5, 'What It Takes', 'Aerosmith', 'Rock', '1989', 'United States', true, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (6, 'Jaded', 'Aerosmith', 'Rock', '2001', 'United States', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (7, 'Shoot to Thrill', 'AC/DC', 'Rock', '1980', 'Australia', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (8, 'November Rain', 'Guns N Roses', 'Rock', '1991', 'United States', true, 2);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (9, 'Nothing Else Matters', 'Metallica', 'Rock', '1991', 'United States', false, 2);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (10, 'Enter Sandman', 'Metallica', 'Rock', '1991', 'United States', false, 2);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (11, 'Realízame mis sueños', 'Binomio de Oro', 'Vallenato', '1999', 'Colombia', false, 2);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (12, 'A Pesar De Todo', 'Binomio de Oro', 'Vallenato', '1997', 'Colombia', true, 2);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (13, 'El Santo Cachón', 'Los Embajadores Vallenatos', 'Vallenato', '2012', 'Colombia', true, 2);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (14, 'Que me Puedas Amar', 'Miguel Morales', 'Vallenato', '1994', 'Colombia', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (15, 'Acompáñame', 'Miguel Morales', 'Vallenato', '1992', 'Colombia', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (16, 'Tres Noches', 'Jesus Manuel Estrada', 'Vallenato', '1998', 'Colombia', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (17, 'Directo Al Corazón', 'Jesus Manuel Estrada', 'Vallenato', '1998', 'Colombia', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (18, 'Lo Dudo', 'José José', 'Balada', '1983', 'México', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (19, 'El Destino', 'Rocío Durcal', 'Balada', '1997', 'España', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (20, 'Tú Lo Decisiste', 'Ana Gabriel', 'Balada', '1994', 'México', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (21, 'Magic', 'Coldplay', 'Pop Rock', '2014', 'England', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (22, 'Paradise', 'Coldplay', 'Pop Rock', '2011', 'England', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (23, 'Speed of Sound', 'Coldplay', 'Pop Rock', '2005', 'England', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (24, 'The Lazy Song', 'Bruno Mars', 'Pop', '2010', 'United States', false, 1);
INSERT INTO song (id, name, artist, genre, year, country, is_private, user_id) VALUES (25, 'One More Night', 'Maroon 5', 'Pop', '2012', 'United States', false, 1);
