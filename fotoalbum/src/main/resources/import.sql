INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Mostra un gruppo di aquile di mare testabianca', 1, 'Dance of the Eagles', '');
INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Il fotografo Rhez Solano ha immortalato questo raduno di pinguini reali nella Gold Harbour Bay della Georgia del Sud.', 0, 'I pinguini reali', '');
INSERT INTO db_fotoalbum.photos (description, visible, title, url) VALUES('Nel maggio 2021, il vulcano Fagradalsfjall, nella penisola islandese di Reykjanes, ha eruttato per la prima volta dopo più di seimila anni. ', 1, 'Il vulcano Fagradalsfjall', '');

INSERT INTO db_fotoalbum.categories (name) VALUES('Animals');
INSERT INTO db_fotoalbum.categories (name) VALUES('Family');
INSERT INTO db_fotoalbum.categories (name) VALUES('Composition');
INSERT INTO db_fotoalbum.categories (name) VALUES('Emotion');
INSERT INTO db_fotoalbum.categories (name) VALUES('Hobbies');
INSERT INTO db_fotoalbum.categories (name) VALUES('Music');
INSERT INTO db_fotoalbum.categories (name) VALUES('Game');
INSERT INTO db_fotoalbum.categories (name) VALUES('Christmas');
INSERT INTO db_fotoalbum.categories (name) VALUES('Halloween');
INSERT INTO db_fotoalbum.categories (name) VALUES('Wedding');

INSERT INTO db_fotoalbum.photo_category (photo_id, category_id) VALUES(1, 1);
