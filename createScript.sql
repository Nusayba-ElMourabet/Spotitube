/*Use master
DROP DATABASE IF EXISTS Spotitube;
CREATE DATABASE Spotitube;
*/
USE Spotitube;

DROP TABLE IF EXISTS PlaylistTracks;
DROP TABLE IF EXISTS Tracks;
DROP TABLE IF EXISTS Playlists; 
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id INT IDENTITY NOT NULL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255) UNIQUE NOT NULL,
    fullname VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE Playlists (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100) NOT NULL,
    owner_id INT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES Users(id)
);

CREATE TABLE Tracks (
    id INT PRIMARY KEY IDENTITY(1,1),
    title VARCHAR(100) NOT NULL,
    performer VARCHAR(100) NOT NULL,
    duration INT NOT NULL,
    album VARCHAR(100),
    playcount INT,
    publication_date DATE,
    description VARCHAR(255),
    offline_available BIT NOT NULL
);

CREATE TABLE PlaylistTracks (
    playlist_id INT,
    track_id INT,
    PRIMARY KEY (playlist_id, track_id),
    FOREIGN KEY (playlist_id) REFERENCES Playlists(id),
    FOREIGN KEY (track_id) REFERENCES Tracks(id)
);

-- Insert Users
INSERT INTO Users (username, password, token, fullname) VALUES ('user', 'password', 'dummy-token', 'userfullname');
INSERT INTO Users (username, password, token, fullname) VALUES ('user', 'password', 'dummy-token', 'Nusayba');


-- Insert Playlists
INSERT INTO Playlists (name, owner_id) VALUES ('Heavy Metal', 1);
INSERT INTO Playlists (name, owner_id) VALUES ('Pop', 1);
INSERT INTO Playlists (name, owner_id) VALUES ('Rock', 1);
INSERT INTO Playlists (name, owner_id) VALUES ('Jazz', 1);

-- Insert Tracks
INSERT INTO Tracks (title, performer, duration, album, playcount, publication_date, description, offline_available) VALUES 
('Track 1', 'Performer 1', 300, 'Album 1', 10, '2020-01-01', 'Description 1', 1),
('Track 2', 'Performer 2', 250, 'Album 2', 20, '2019-01-01', 'Description 2', 0),
('Track 3', 'Performer 3', 200, 'Album 3', 30, '2018-01-01', 'Description 3', 1),
('Track 4', 'Performer 4', 350, 'Album 4', 40, '2017-01-01', 'Description 4', 0),
('Track 5', 'Performer 5', 400, 'Album 5', 50, '2016-01-01', 'Description 5', 1),
('Track 6', 'Performer 6', 150, 'Album 6', 60, '2015-01-01', 'Description 6', 0),
('Track 7', 'Performer 7', 300, 'Album 7', 70, '2014-01-01', 'Description 7', 1);


-- Insert PlaylistTracks (Associating tracks with playlists)
INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (1, 1);
INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (1, 2);
INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (2, 3);
INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (2, 4);
INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (3, 5);
INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (3, 6);
INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (4, 7);
