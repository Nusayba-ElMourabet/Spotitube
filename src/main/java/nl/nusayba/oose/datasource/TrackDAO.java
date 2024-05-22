package nl.nusayba.oose.datasource;


import jakarta.enterprise.context.ApplicationScoped;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.interfaces.ITrackDAO;
import jakarta.inject.Inject;
import nl.nusayba.oose.util.DatabaseProperties;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

@ApplicationScoped
public class TrackDAO implements ITrackDAO {

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
    public List<TrackDTO> getAllTracks() {
        List<TrackDTO> tracks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Tracks")) {

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
                tracks.add(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    @Override
    public TrackDTO getTrackById(int id) {
        TrackDTO track = null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tracks WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    track = new TrackDTO();
                    track.setId(resultSet.getInt("id"));
                    track.setTitle(resultSet.getString("title"));
                    track.setPerformer(resultSet.getString("performer"));
                    track.setDuration(resultSet.getInt("duration"));
                    track.setAlbum(resultSet.getString("album"));
                    track.setPlaycount(resultSet.getInt("playcount"));
                    track.setPublicationDate(resultSet.getString("publication_date"));
                    track.setDescription(resultSet.getString("description"));
                    track.setOfflineAvailable(resultSet.getBoolean("offline_available"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return track;
    }

    @Override
    public void insertTrack(TrackDTO track) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Tracks (title, performer, duration, album, playcount, publication_date, description, offline_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, track.getTitle());
            statement.setString(2, track.getPerformer());
            statement.setInt(3, track.getDuration());
            statement.setString(4, track.getAlbum());
            statement.setInt(5, track.getPlaycount());
            statement.setDate(6, Date.valueOf(track.getPublicationDate()));
            statement.setString(7, track.getDescription());
            statement.setBoolean(8, track.isOfflineAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTrack(TrackDTO track) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("UPDATE Tracks SET title = ?, performer = ?, duration = ?, album = ?, playcount = ?, publication_date = ?, description = ?, offline_available = ? WHERE id = ?")) {
            statement.setString(1, track.getTitle());
            statement.setString(2, track.getPerformer());
            statement.setInt(3, track.getDuration());
            statement.setString(4, track.getAlbum());
            statement.setInt(5, track.getPlaycount());
            statement.setDate(6, Date.valueOf(track.getPublicationDate()));
            statement.setString(7, track.getDescription());
            statement.setBoolean(8, track.isOfflineAvailable());
            statement.setInt(9, track.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrack(int id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Tracks WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
