//package nl.nusayba.oose.domain.services;
//
//import nl.nusayba.oose.datasource.PlaylistDAO;
//import nl.nusayba.oose.datasource.TrackDAO;
//import nl.nusayba.oose.domain.dto.PlaylistDTO;
//import nl.nusayba.oose.domain.dto.TrackDTO;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//
//public class PlaylistServiceTest {
//
//    @InjectMocks
//    private PlaylistService playlistService;
//
//    @Mock
//    private PlaylistDAO playlistDAO;
//
//    @Mock
//    private TrackDAO trackDAO;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetPlaylistsWithValidToken() {
//        // Arrange
//        List<PlaylistDTO> mockPlaylists = Arrays.asList(
//                new PlaylistDTO(1, "Heavy Metal", true, Arrays.asList()),
//                new PlaylistDTO(2, "Pop", false, Arrays.asList())
//        );
//        when(playlistDAO.getPlaylists()).thenReturn(mockPlaylists);
//
//        // Act
//        List<PlaylistDTO> playlists = playlistService.getPlaylists("dummy-token");
//
//        // Assert
//        assertNotNull(playlists);
//        assertEquals(2, playlists.size());
//    }
//
//    @Test
//    public void testGetPlaylistsWithInvalidToken() {
//        // Arrange - No arrangement needed as we expect null result
//
//        // Act
//        List<PlaylistDTO> playlists = playlistService.getPlaylists("invalid-token");
//
//        // Assert
//        assertNull(playlists);
//    }
//
//    @Test
//    public void testGetPlaylistByIdWithValidToken() {
//        // Arrange
//        PlaylistDTO mockPlaylist = new PlaylistDTO(1, "Heavy Metal", true, Arrays.asList());
//        when(playlistDAO.getPlaylistById(1)).thenReturn(mockPlaylist);
//
//        TrackDTO track1 = new TrackDTO();
//        track1.setId(1);
//        track1.setTitle("Master of Puppets");
//        track1.setPerformer("Metallica");
//        track1.setDuration(515);
//        track1.setAlbum("Master of Puppets");
//        track1.setPlaycount(0);
//        track1.setPublicationDate(null);
//        track1.setDescription(null);
//        track1.setOfflineAvailable(false);
//
//        TrackDTO track2 = new TrackDTO();
//        track2.setId(2);
//        track2.setTitle("Back in Black");
//        track2.setPerformer("AC/DC");
//        track2.setDuration(255);
//        track2.setAlbum("Back in Black");
//        track2.setPlaycount(37);
//        track2.setPublicationDate("25-07-1980");
//        track2.setDescription("Classic rock song");
//        track2.setOfflineAvailable(true);
//
//        List<TrackDTO> mockTracks = Arrays.asList(track1, track2);
//        when(trackDAO.getTracks()).thenReturn(mockTracks);
//
//        // Act
//        PlaylistDTO playlist = playlistService.getPlaylistById("dummy-token", 1);
//
//        // Assert
//        assertNotNull(playlist);
//        assertEquals(1, playlist.getId());
//        assertEquals("Heavy Metal", playlist.getName());
//        assertEquals(2, playlist.getTracks().size());
//    }
//
//    @Test
//    public void testGetPlaylistByIdWithInvalidToken() {
//        // Arrange - No arrangement needed as we expect null result
//
//        // Act
//        PlaylistDTO playlist = playlistService.getPlaylistById("invalid-token", 1);
//
//        // Assert
//        assertNull(playlist);
//    }
//
//    @Test
//    public void testGetPlaylistByIdWithInvalidId() {
//        // Arrange
//        when(playlistDAO.getPlaylistById(999)).thenReturn(null);
//
//        // Act
//        PlaylistDTO playlist = playlistService.getPlaylistById("dummy-token", 999);
//
//        // Assert
//        assertNull(playlist);
//    }
//}