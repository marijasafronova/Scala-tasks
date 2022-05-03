--SELECT * FROM artists a
----WHERE Name LIKE 'M%'
--WHERE Name GLOB '*Ma*'
--ORDER BY ArtistId DESC
--LIMIT 20;
--
--INSERT INTO artists (Name)
--VALUES ('Marijka');
--
--UPDATE artists 
--SET name = 'Marija'
--WHERE ArtistId = 290;
--
--UPDATE artists 
--SET name = 'Masha'
--WHERE Name GLOB '*Mar*';
--
--DELETE FROM artists 
--WHERE ArtistId = 290;

INSERT INTO genres (Name)
VALUES ('Indie Rock');

SELECT * FROM genres g ;

INSERT INTO artists (Name)
VALUES ('Imagine Dragons');

SELECT * FROM artists a ;

INSERT INTO albums (Title, ArtistId)
VALUES ('Gold and Mirrors', 291);
SELECT * FROM albums a ;

INSERT INTO tracks (Name, AlbumId)
VALUES ('Dream', 348);
INSERT INTO tracks (Name, AlbumId)
VALUES ('Gold and Mirrors', 348);
SELECT * FROM tracks t WHERE AlbumId  = 348;
