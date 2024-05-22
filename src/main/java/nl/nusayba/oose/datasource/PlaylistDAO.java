package nl.nusayba.oose.datasource;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.interfaces.IPlaylistDAO;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import nl.nusayba.oose.util.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PlaylistDAO implements IPlaylistDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Override
    public PlaylistsDTO getPlaylist(String user) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("SELECT p.id, p.name, u.username FROM playlists p JOIN users u ON p.owner_id = u.id");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                PlaylistDTO playlist = new PlaylistDTO();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getString("username").equals(user));
                List<TrackDTO> tracks = new ArrayList<>();
                playlist.setTracks(tracks);
                playlistsDTO.addPlaylist(playlist);
            }

            playlistsDTO.setLength(calculateTotalDuration());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return playlistsDTO;
    }

    @Override
    public int calculateTotalDuration() {
        int totalDuration = 0;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("SELECT SUM(t.duration) AS length FROM PlaylistTracks pt JOIN tracks t ON pt.track_id = t.id");
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
    public void updatePlaylist(int id, PlaylistDTO playlistDTO) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("UPDATE playlists SET name = ? WHERE id = ?")) {

            statement.setString(1, playlistDTO.getName());
            statement.setInt(2, id);
            statement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public void addPlaylist(String user, PlaylistDTO playlistDTO) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("INSERT INTO playlists (name, owner_id) VALUES (?, (SELECT id FROM users WHERE username = ?))")) {

            statement.setString(1, playlistDTO.getName());
            statement.setString(2, user);
            statement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public void deletePlaylist(int id) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("DELETE FROM playlists WHERE id = ?")) {

            deleteTracksFromPlaylist(id);

            statement.setInt(1, id);
            statement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    private void deleteTracksFromPlaylist(int id) {
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("DELETE FROM PlaylistTracks WHERE track_id = ?")) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public PlaylistDTO getPlaylistById(int id) {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT p.id, p.name, u.username " +
                             "FROM playlists p JOIN users u ON p.owner_id = u.id " +
                             "WHERE p.id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playlistDTO.setId(resultSet.getInt("id"));
                playlistDTO.setName(resultSet.getString("name"));
                playlistDTO.setTracks(new ArrayList<>());              }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return playlistDTO;
    }
}
