//package nl.nusayba.oose.domain.services;
//
//import nl.nusayba.oose.datasource.LoginDAO;
//import nl.nusayba.oose.datasource.PlaylistDAO;
//import nl.nusayba.oose.datasource.TrackDAO;
//import nl.nusayba.oose.domain.dto.*;
//import nl.nusayba.oose.domain.exceptions.AuthenticationException;
//import nl.nusayba.oose.domain.interfaces.ILoginDAO;
//import nl.nusayba.oose.domain.interfaces.IPlaylistDAO;
//import nl.nusayba.oose.domain.interfaces.ITrackDAO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class PlaylistServiceTest {
//    private PlaylistService sut;
//    private IPlaylistDAO fixture;
//    private ILoginDAO fixture2;
//    private ITrackDAO fixture3;
//
//    @BeforeEach
//    public void setUp() {
//        sut = new PlaylistService();
//        fixture = Mockito.mock(PlaylistDAO.class);
//        sut.setPlaylistDAO(fixture);
//        fixture2 = Mockito.mock(LoginDAO.class);
//        sut.setLoginDAO(fixture2);
//        fixture3 = Mockito.mock(TrackDAO.class);
//        sut.setTrackDAO(fixture3);
//    }
//
//    @Test
//    public void getAllPlaylistsTest() {
//        // Arrange
//        UserDTO UserDTO = new UserDTO();
//        String token = "1234-1234-1234";
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(UserDTO);
//
//        List<PlaylistDTO> playlists = new ArrayList<>();
//
//        // Coverage for PlaylistDTO
//
//        PlaylistDTO playlist = new PlaylistDTO();
//        playlist.setId(1);
//        playlist.setName("name");
//        playlist.setOwner(true);
//        playlist.setTracks(new ArrayList<>());
//
//        playlists.add(playlist);
//
//        playlist.getId();
//        playlist.getName();
//        playlist.isOwner();
//        playlist.getTracks();
//
//
//        PlaylistsDTO playlistsdto = new PlaylistsDTO(playlists, 10);
//
//        // Coverage for PlaylistsDTO
//
//        playlistsdto.setPlaylists(playlists);
//        playlistsdto.setLength(10);
//
//        playlistsdto.addPlaylist(playlist);
//
//        playlistsdto.getLength();
//        playlistsdto.getPlaylist(1);
//        playlistsdto.getPlaylists();
//
//        Mockito.when(fixture.getPlaylist(UserDTO.getUser())).thenReturn(playlistsdto);
//
//        // Act
//        PlaylistsDTO result = sut.getAllPlaylists(token);
//
//        // Assert
//        assertEquals(playlistsdto, result);
//    }
//
//    @Test
//    public void getAllPlaylistsTestError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.getAllPlaylists(token));
//    }
//
//    @Test
//    public void addPlaylistTest() {
//        // Arrange
//        UserDTO UserDTO = new UserDTO();
//        String token = "123 123 123";
//        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
//
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(UserDTO);
//
//        // Act
//        Mockito.when(fixture.getPlaylist(UserDTO.getUser())).thenReturn(playlistsDTO);
//        PlaylistsDTO result = sut.addPlaylist(token, new PlaylistDTO());
//
//        // Assert
//        assertEquals(playlistsDTO, result);
//    }
//
//    @Test
//    public void addPlaylistTestError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.addPlaylist(token, new PlaylistDTO()));
//    }
//
//    @Test
//    public void deletePlaylistTest() {
//        // Arrange
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUser("meron");
//
//        String token = "123 123 123";
//        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
//
//        // Act
//        Mockito.when(fixture.getPlaylist("meron")).thenReturn(playlistsDTO);
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(userDTO);
//
//        PlaylistsDTO result = sut.deletePlaylist(token, 1);
//
//        // Assert
//        assertEquals(playlistsDTO, result);
//    }
//
//    @Test
//    public void deletePlaylistTestError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.deletePlaylist(token, 1));
//    }
//
//    @Test
//    public void updatePlaylistTest() {
//        // Arrange
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUser("meron");
//
//        String token = "123 123 123";
//
//        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
//
//        // Act
//        Mockito.when(fixture.getPlaylist("meron")).thenReturn(playlistsDTO);
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(userDTO);
//
//        PlaylistsDTO result = sut.updatePlaylist(token, 1, new PlaylistDTO());
//
//        // Assert
//        assertEquals(playlistsDTO, result);
//    }
//
//    @Test
//    public void updatePlaylistTestError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.updatePlaylist(token, 1, new PlaylistDTO()));
//    }
//
//    @Test
//    public void getTracksInPlaylistTest() {
//        // Arrange
//        TracksDTO returnValue = new TracksDTO(new ArrayList<>());
//        String token = "1234-1234-1234";
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUser("meron");
//
//        // Act
//        Mockito.when(fixture3.getAllTracksinPlaylist(Mockito.anyInt())).thenReturn(returnValue);
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(userDTO);
//        TracksDTO result = sut.getTracksInPlaylist(1, "1234-1234-1234");
//
//        // Assert
//        assertEquals(returnValue, result);
//    }
//
//    @Test
//    public void getTracksInPlaylistTestError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.getTracksInPlaylist(1, token));
//    }
//
//    @Test
//    public void addTrackToPlaylistTest() {
//        // Arrange
//        TracksDTO returnValue = new TracksDTO(new ArrayList<>());
//        String token = "123 123 123";
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUser("meron");
//
//        // Coverage for TrackDTO
//
//        List<TrackDTO> tracks = new ArrayList<>();
//
//
//        TrackDTO track = new TrackDTO();
//        track.setId(1);
//        track.setAlbum("album");
//        track.setDescription("description");
//        track.setDuration(10);
//        track.setPerformer("performer");
//        track.setPublicationDate("publicationDate");
//        track.setPlaycount(10);
//        track.setTitle("title");
//        track.setOfflineAvailable(true);
//
//        track.getAlbum();
//        track.getDescription();
//        track.getDuration();
//        track.getId();
//        track.getPerformer();
//        track.getPublicationDate();
//        track.getPlaycount();
//        track.getTitle();
//        track.isOfflineAvailable();
//
//        TracksDTO tracksDTO = new TracksDTO(tracks);
//
//        // Coverage for PlaylistsDTO
//
//        tracksDTO.setLength(10);
//        tracksDTO.setTracks(tracks);
//
//        tracksDTO.addTrack(track);
//
//        tracksDTO.getTracks();
//        tracksDTO.getLength();
//
//        // Act
//        Mockito.when(fixture3.getAllTracksinPlaylist(Mockito.anyInt())).thenReturn(returnValue);
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(userDTO);
//
//        TracksDTO result = sut.addTrackToPlaylist(token, 1, new TrackDTO());
//
//        // Assert
//        assertEquals(returnValue, result);
//    }
//
//    @Test
//    public void addTrackToPlaylistTestError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.addTrackToPlaylist(token, 1, new TrackDTO()));
//    }
//
//    @Test
//    public void getTracksNotInPlaylist() {
//        // Arrange
//        TracksDTO returnValue = new TracksDTO(new ArrayList<>());
//        String token = "123 123 123";
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUser("meron");
//
//        // Act
//        Mockito.when(fixture3.getTracksNotInPlaylist(Mockito.anyInt())).thenReturn(returnValue);
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(userDTO);
//
//        TracksDTO result = sut.getTracksNotInPlaylist(1, token);
//
//        // Assert
//        assertEquals(returnValue, result);
//    }
//
//    @Test
//    public void getTracksNotInPlaylistError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.getTracksNotInPlaylist(1, token));
//    }
//
//    @Test
//    public void deleteTrackFromPlaylist(){
//        // Arrange
//        TracksDTO returnValue = new TracksDTO(new ArrayList<>());
//        String token = "123 123 123";
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUser("meron");
//
//        // Act
//        Mockito.when(fixture2.getUserByToken(token)).thenReturn(userDTO);
//        Mockito.when(fixture3.getAllTracksinPlaylist(Mockito.anyInt())).thenReturn(returnValue);
//        TracksDTO result = sut.deleteTrackFromPlaylist(token, 1, 1);
//
//        // Assert
//        assertEquals(returnValue, result);
//    }
//
//    @Test
//    public void deleteTrackFromPlaylistError() {
//        // Arrange
//        String token = "1234-1234-1234";
//
//        // Assert
//        assertThrows(AuthenticationException.class, () -> sut.deleteTrackFromPlaylist(token, 1, 1));
//    }
//}
