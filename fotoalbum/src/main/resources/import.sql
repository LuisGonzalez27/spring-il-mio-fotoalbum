INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Un bosco con un lago', 1, 'Un bosco', 'https://picsum.photos/id/10/300/400');
INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Dentro al bosco', 1, 'Bosco', 'https://picsum.photos/id/11/300/400');
INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Una spiaggia', 1, 'Spiaggia', 'https://picsum.photos/id/12/300/400');
INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Spiaggia con pietre', 1, 'Spiaggia brutta', 'https://picsum.photos/id/13/300/400');
INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Muso di gatto', 1, 'Gatto', 'https://picsum.photos/id/40/300/400');
INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Una cascata è quel punto in cui la acqua di un fiume o di un torrente', 1, 'Cascata', 'https://picsum.photos/id/15/300/400');

INSERT INTO db_fotoalbum.categories (name) VALUES('Nature');
INSERT INTO db_fotoalbum.categories (name) VALUES('Animals');
INSERT INTO db_fotoalbum.categories (name) VALUES('Family');
INSERT INTO db_fotoalbum.categories (name) VALUES('Composition');
INSERT INTO db_fotoalbum.categories (name) VALUES('Emotion');
INSERT INTO db_fotoalbum.categories (name) VALUES('Hobbies');
INSERT INTO db_fotoalbum.categories (name) VALUES('Music');
INSERT INTO db_fotoalbum.categories (name) VALUES('Game');
INSERT INTO db_fotoalbum.categories (name) VALUES('Christmas');
INSERT INTO db_fotoalbum.categories (name) VALUES('Halloween');

INSERT INTO db_fotoalbum.photo_category (photo_id, category_id) VALUES(1, 1);
INSERT INTO db_fotoalbum.photo_category (photo_id, category_id) VALUES(2, 1);
INSERT INTO db_fotoalbum.photo_category (photo_id, category_id) VALUES(3, 1);
INSERT INTO db_fotoalbum.photo_category (photo_id, category_id) VALUES(4, 1);
INSERT INTO db_fotoalbum.photo_category (photo_id, category_id) VALUES(5, 2);

INSERT INTO db_fotoalbum.users (username, password) VALUES('Admin', '{noop}luis');
INSERT INTO db_fotoalbum.users (username, password) VALUES('Luis','{noop}miguel');

INSERT INTO db_fotoalbum.roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO db_fotoalbum.roles (id, name) VALUES(2, 'USER');

INSERT INTO db_fotoalbum.users_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO db_fotoalbum.users_roles(user_id, roles_id) VALUES(2, 2);

INSERT INTO db_fotoalbum.contacts (email, message)VALUES('admin@tiscali.it', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which dont look even slightly believable.');
INSERT INTO db_fotoalbum.contacts (email, message)VALUES('luis@tiscali.it', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form');
INSERT INTO db_fotoalbum.contacts (email, message)VALUES('miguel@tiscali.it', 'There are many variations of passages of Lorem Ipsum available.');
