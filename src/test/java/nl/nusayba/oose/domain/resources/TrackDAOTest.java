package nl.nusayba.oose.domain.resources;

import nl.nusayba.oose.datasource.TrackDAO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.util.DatabaseProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackDAOTest {

    @InjectMocks
    private TrackDAO trackDAO;

    @Mock
    private DatabaseProperties databaseProperties;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(databaseProperties.connectionString()).thenReturn("jdbc:h2:mem:testdb");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    public void testGetAllTracks() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("Test Title");
        when(mockResultSet.getString("performer")).thenReturn("Test Performer");
        when(mockResultSet.getInt("duration")).thenReturn(300);
        when(mockResultSet.getString("album")).thenReturn("Test Album");
        when(mockResultSet.getInt("playcount")).thenReturn(100);
        when(mockResultSet.getString("publication_date")).thenReturn("2022-01-01");
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getBoolean("offline_available")).thenReturn(true);

        TracksDTO tracksDTO = trackDAO.getAllTracks();

        assertNotNull(tracksDTO);
        assertEquals(1, tracksDTO.getTracks().size());

        TrackDTO track = tracksDTO.getTracks().get(0);
        assertEquals(1, track.getId());
        assertEquals("Test Title", track.getTitle());
        assertEquals("Test Performer", track.getPerformer());
        assertEquals(300, track.getDuration());
        assertEquals("Test Album", track.getAlbum());
        assertEquals(100, track.getPlaycount());
        assertEquals("2022-01-01", track.getPublicationDate());
        assertEquals("Test Description", track.getDescription());
        assertTrue(track.isOfflineAvailable());
    }

    @Test
    public void testAddTrack() throws SQLException {
        TrackDTO trackDTO = new TrackDTO(1, "Test Title", "Test Performer", 300, "Test Album", 100, "2022-01-01", "Test Description", true);

        trackDAO.addTrack(trackDTO);

        verify(mockStatement, times(1)).setString(1, trackDTO.getTitle());
        verify(mockStatement, times(1)).setString(2, trackDTO.getPerformer());
        verify(mockStatement, times(1)).setInt(3, trackDTO.getDuration());
        verify(mockStatement, times(1)).setString(4, trackDTO.getAlbum());
        verify(mockStatement, times(1)).setInt(5, trackDTO.getPlaycount());
        verify(mockStatement, times(1)).setString(6, trackDTO.getPublicationDate());
        verify(mockStatement, times(1)).setString(7, trackDTO.getDescription());
        verify(mockStatement, times(1)).setBoolean(8, trackDTO.isOfflineAvailable());
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteTrack() throws SQLException {
        int trackId = 1;

        trackDAO.deleteTrack(trackId);

        verify(mockStatement, times(1)).setInt(1, trackId);
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateTrack() throws SQLException {
        int trackId = 1;
        TrackDTO trackDTO = new TrackDTO(1, "Updated Title", "Updated Performer", 350, "Updated Album", 150, "2022-01-02", "Updated Description", false);

        trackDAO.updateTrack(trackId, trackDTO);

        verify(mockStatement, times(1)).setString(1, trackDTO.getTitle());
        verify(mockStatement, times(1)).setString(2, trackDTO.getPerformer());
        verify(mockStatement, times(1)).setInt(3, trackDTO.getDuration());
        verify(mockStatement, times(1)).setString(4, trackDTO.getAlbum());
        verify(mockStatement, times(1)).setInt(5, trackDTO.getPlaycount());
        verify(mockStatement, times(1)).setString(6, trackDTO.getPublicationDate());
        verify(mockStatement, times(1)).setString(7, trackDTO.getDescription());
        verify(mockStatement, times(1)).setBoolean(8, trackDTO.isOfflineAvailable());
        verify(mockStatement, times(1)).setInt(9, trackId);
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetTrackById() throws SQLException {
        int trackId = 1;
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(trackId);
        when(mockResultSet.getString("title")).thenReturn("Test Title");
        when(mockResultSet.getString("performer")).thenReturn("Test Performer");
        when(mockResultSet.getInt("duration")).thenReturn(300);
        when(mockResultSet.getString("album")).thenReturn("Test Album");
        when(mockResultSet.getInt("playcount")).thenReturn(100);
        when(mockResultSet.getString("publication_date")).thenReturn("2022-01-01");
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getBoolean("offline_available")).thenReturn(true);

        TrackDTO trackDTO = trackDAO.getTrackById(trackId);

        assertNotNull(trackDTO);
        assertEquals(trackId, trackDTO.getId());
        assertEquals("Test Title", trackDTO.getTitle());
        assertEquals("Test Performer", trackDTO.getPerformer());
        assertEquals(300, trackDTO.getDuration());
        assertEquals("Test Album", trackDTO.getAlbum());
        assertEquals(100, trackDTO.getPlaycount());
        assertEquals("2022-01-01", trackDTO.getPublicationDate());
        assertEquals("Test Description", trackDTO.getDescription());
        assertTrue(trackDTO.isOfflineAvailable());
    }
}
