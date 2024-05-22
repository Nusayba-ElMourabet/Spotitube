package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.datasource.TrackDAO;
import  nl.nusayba.oose.domain.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
public class TrackServiceTest {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private TrackDAO trackDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTracksWithValidToken() {
        // Arrange
        TrackDTO track1 = new TrackDTO(1, "Master of Puppets", "Metallica", 515, "Master of Puppets", 0, null, null, false);
        TrackDTO track2 = new TrackDTO(2, "Back in Black", "AC/DC", 255, "Back in Black", 37, "25-07-1980", "Classic rock song", true);

        List<TrackDTO> mockTracks = Arrays.asList(track1, track2);
        when(trackDAO.getTracks()).thenReturn(mockTracks);

        // Act
        List<TrackDTO> tracks = trackService.getTracks("dummy-token");

        // Assert
        assertNotNull(tracks);
        assertEquals(2, tracks.size());
    }

    @Test
    public void testGetTracksWithInvalidToken() {
        // Arrange - No arrangement needed as we expect null result

        // Act
        List<TrackDTO> tracks = trackService.getTracks("invalid-token");

        // Assert
        assertNull(tracks);
    }
}