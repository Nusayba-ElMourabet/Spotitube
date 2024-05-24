package nl.nusayba.oose.domain.resources;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import nl.nusayba.oose.resources.TrackResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackResourceTest{
    private TrackResource sut;
    private PlaylistService fixture;

    @BeforeEach
    public void setUp() {
        sut = new TrackResource();
        fixture = Mockito.mock(PlaylistService.class);
        sut.setPlaylistService(fixture);
    }

    @Test
    public void getAvailableTracksTest() {
        // Arrange
        TracksDTO returnValue = new TracksDTO(new ArrayList<>());

        // Act
        Mockito.when(fixture.getTracksNotInPlaylist(Mockito.anyInt(), Mockito.anyString())).thenReturn(returnValue);
        TracksDTO result = sut.getTracksNotInPlaylist("1234-1234-1234", 1);

        // Assert
        assertEquals(returnValue, result);
    }
}
