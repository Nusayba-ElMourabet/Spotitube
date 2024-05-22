package nl.nusayba.oose.datasource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.interfaces.IPlaylistDAO;
import nl.nusayba.oose.util.DatabaseProperties;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;


@ApplicationScoped
public class PlaylistDAO implements IPlaylistDAO {

    @Inject
    private DatabaseProperties dbProperties;

    private String url;
    private String username;
    private String password;

    @Inject
    public void init() {
        this.url = dbProperties.getUrl();
        this.username = dbProperties.getUsername();
        this.password = dbProperties.getPassword();
    }

    @Override
    public List<PlaylistDTO> getAllPlaylists() {
        List<PlaylistDTO> playlists = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Playlists")) {

            while (resultSet.next()) {
                PlaylistDTO playlist = new PlaylistDTO();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getInt("owner_id") == 1);
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    @Override
    public PlaylistDTO getPlaylistById(int id) {
        PlaylistDTO playlist = null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Playlists WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    playlist = new PlaylistDTO();
                    playlist.setId(resultSet.getInt("id"));
                    playlist.setName(resultSet.getString("name"));
                    playlist.setOwner(resultSet.getInt("owner_id") == 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlist;
    }

    @Override
    public void insertPlaylist(PlaylistDTO playlist) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Playlists (name, owner_id) VALUES (?, ?)")) {
            statement.setString(1, playlist.getName());
            statement.setInt(2, playlist.isOwner() ? 1 : 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlaylist(PlaylistDTO playlist) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("UPDATE Playlists SET name = ?, owner_id = ? WHERE id = ?")) {
            statement.setString(1, playlist.getName());
            statement.setInt(2, playlist.isOwner() ? 1 : 0);
            statement.setInt(3, playlist.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlaylist(int id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Playlists WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
