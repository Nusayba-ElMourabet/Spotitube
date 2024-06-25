package nl.nusayba.oose.datasource;

import nl.nusayba.oose.domain.dto.LoginDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.interfaces.ITrackDAO;
import nl.nusayba.oose.util.DatabaseProperties;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sound.midi.Track;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class TrackDAO implements ITrackDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties() {
        this.databaseProperties = DatabaseProperties.getInstance();
    }

    @Override
    public TracksDTO getAllTracksinPlaylist(int playlistId) {
        TracksDTO tracks = new TracksDTO();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks t join PlaylistTracks p on t.id = p.track_id where p.playlist_id = ?");
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getString("publication_date"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offline_available"));
                tracks.addTrack(track);
            }
            tracks.setLength(calculateTotalDuration());

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tracks;
    }

    @Override
    public TracksDTO getAllTracks() {
        TracksDTO tracksDTO = new TracksDTO();
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getString("publication_date"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offline_available"));
                tracksDTO.addTrack(track);
            }
            tracksDTO.setLength(calculateTotalDuration());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return tracksDTO;
    }

    @Override
    public void addTrack(TrackDTO trackDTO) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("INSERT INTO tracks (title, performer, duration, album, playcount, publication_date, description, offline_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, trackDTO.getTitle());
            statement.setString(2, trackDTO.getPerformer());
            statement.setInt(3, trackDTO.getDuration());
            statement.setString(4, trackDTO.getAlbum());
            statement.setInt(5, trackDTO.getPlaycount());
            statement.setString(6, trackDTO.getPublicationDate());
            statement.setString(7, trackDTO.getDescription());
            statement.setBoolean(8, trackDTO.isOfflineAvailable());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public void deleteTrack(int id) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("DELETE FROM tracks WHERE id = ?")) {

            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public void updateTrack(int id, TrackDTO trackDTO) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("UPDATE tracks SET title = ?, performer = ?, duration = ?, album = ?, playcount = ?, publication_date = ?, description = ?, offline_available = ? WHERE id = ?")) {

            statement.setString(1, trackDTO.getTitle());
            statement.setString(2, trackDTO.getPerformer());
            statement.setInt(3, trackDTO.getDuration());
            statement.setString(4, trackDTO.getAlbum());
            statement.setInt(5, trackDTO.getPlaycount());
            statement.setString(6, trackDTO.getPublicationDate());
            statement.setString(7, trackDTO.getDescription());
            statement.setBoolean(8, trackDTO.isOfflineAvailable());
            statement.setInt(9, id);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public TrackDTO getTrackById(int id) {
        TrackDTO trackDTO = new TrackDTO();
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tracks WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trackDTO.setId(resultSet.getInt("id"));
                trackDTO.setTitle(resultSet.getString("title"));
                trackDTO.setPerformer(resultSet.getString("performer"));
                trackDTO.setDuration(resultSet.getInt("duration"));
                trackDTO.setAlbum(resultSet.getString("album"));
                trackDTO.setPlaycount(resultSet.getInt("playcount"));
                trackDTO.setPublicationDate(resultSet.getString("publication_date"));
                trackDTO.setDescription(resultSet.getString("description"));

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return trackDTO;
    }

    @Override
    public TracksDTO getTracksNotInPlaylist(int playlistId) {
        List<TrackDTO> t = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM tracks t WHERE t.id NOT IN (SELECT pt.track_id FROM PlaylistTracks pt WHERE pt.playlist_id = ?)")) {
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getString("publication_date"));
                track.setDescription(resultSet.getString("description"));
                t.add(track);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return new TracksDTO(t);
    }

    private int calculateTotalDuration() {
        int totalDuration = 0;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("SELECT SUM(duration) AS length FROM tracks");
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totalDuration = resultSet.getInt("length");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return totalDuration;
    }

    @Override
    public void addTrackToPlaylist(int id, TrackDTO trackDTO){
        try {

            System.out.println("Adding track to playlist");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PlaylistTracks (playlist_id, track_id, offline_available) VALUES (?, ?, ?)");
            statement.setInt(1, id);
            statement.setInt(2, trackDTO.getId());
            statement.setBoolean(3, trackDTO.isOfflineAvailable()); // Adding the offline availability status

            statement.executeUpdate();
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public void deleteTrackFromPlaylist(int id, int trackId) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("DELETE FROM PlaylistTracks WHERE playlist_id = ? AND track_id = ?");
            statement.setInt(1, id);
            statement.setInt(2, trackId);

            statement.executeUpdate();
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }
}
