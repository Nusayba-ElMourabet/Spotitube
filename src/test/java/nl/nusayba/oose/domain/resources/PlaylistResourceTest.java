package nl.nusayba.oose.domain.resources;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import nl.nusayba.oose.resources.PlaylistResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistResourceTest {
    private PlaylistResource sut;
    private PlaylistService fixture;

    @BeforeEach
    public void setup() {
        sut = new PlaylistResource();
        fixture = Mockito.mock(PlaylistService.class);
        sut.setPlaylistService(fixture);
    }

    @Test
    public void getPlaylistTest() {
        // Arrange
        PlaylistsDTO returnValue = new PlaylistsDTO();

        // Act
        Mockito.when(fixture.getAllPlaylists(Mockito.anyString())).thenReturn(returnValue);
        PlaylistsDTO result = sut.getAllPlaylists("1234-1234-1234");

        // Assert
        assertEquals(returnValue, result);
    }

    @Test
    public void addPlaylistTest() {
        // Arrange
        PlaylistsDTO returnValue = new PlaylistsDTO();

        // Act
        Mockito.when(fixture.addPlaylist(Mockito.anyString(), Mockito.any())).thenReturn(returnValue);
        PlaylistsDTO result = sut.addPlaylist("token", new PlaylistDTO());

        // Assert
        assertEquals(returnValue, result);
    }

    @Test
    public void deletePlaylistTest() {
        // Arrange
        PlaylistsDTO returnValue = new PlaylistsDTO();

        // Act
        Mockito.when(fixture.deletePlaylist(Mockito.anyString(), Mockito.anyInt())).thenReturn(returnValue);
        PlaylistsDTO result = sut.deletePlaylist(1, "token");

        // Assert
        assertEquals(returnValue, result);
    }

    @Test
    public void updatePlaylistTest() {
        // Arrange
        PlaylistsDTO returnValue = new PlaylistsDTO();

        // Act
        Mockito.when(fixture.updatePlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.any())).thenReturn(returnValue);
        PlaylistsDTO result = sut.updatePlaylist("1234-1234-1234", 1, new PlaylistDTO());

        // Assert
        assertEquals(returnValue, result);
    }

    @Test
    public void getTracksInPlaylistTest() {
        // Arrange
        TracksDTO returnValue = new TracksDTO(new ArrayList<>());

        // Act
        Mockito.when(fixture.getTracksInPlaylist(Mockito.anyInt(), Mockito.anyString())).thenReturn(returnValue);
        TracksDTO result = sut.getTracksInPlaylist(1, "1234-1234-1234");

        // Assert
        assertEquals(returnValue, result);
    }

    @Test
    public void addTrackToPlaylistTest() {
        // Arrange
        TracksDTO returnValue = new TracksDTO(new ArrayList<>());

        // Act
        Mockito.when(fixture.addTrackToPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.any())).thenReturn(returnValue);
        TracksDTO result = sut.addTrackToPlaylist("1234-1234-1234", 1, new TrackDTO());

        // Assert
        assertEquals(returnValue, result);
    }

    @Test
    public void deleteTrackFromPlaylistTest() {
        // Arrange
        TracksDTO returnValue = new TracksDTO(new ArrayList<>());

        // Act
        Mockito.when(fixture.deleteTrackFromPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(returnValue);
        TracksDTO result = sut.deleteTrackFromPlaylist("token", 1, 1);

        // Assert
        assertEquals(returnValue, result);
    }
}
