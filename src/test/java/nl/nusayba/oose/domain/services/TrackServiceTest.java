package nl.nusayba.oose.domain.services;

import  nl.nusayba.oose.domain.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrackServiceTest {

    @InjectMocks
    private TrackService trackService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTracksWithValidToken() {
        List<TrackDTO> tracks = trackService.getTracks("dummy-token");
        assertNotNull(tracks);
        assertEquals(8, tracks.size());

        TrackDTO firstTrack = tracks.get(0);
        assertEquals(1, firstTrack.getId());
        assertEquals("Master of Puppets", firstTrack.getTitle());
        assertEquals("Metallica", firstTrack.getPerformer());

        TrackDTO secondTrack = tracks.get(1);
        assertEquals(2, secondTrack.getId());
        assertEquals("Back in Black", secondTrack.getTitle());
        assertEquals("AC/DC", secondTrack.getPerformer());

        // Additional assertions for other tracks can be added similarly
    }

    @Test
    public void testGetTracksWithInvalidToken() {
        List<TrackDTO> tracks = trackService.getTracks("invalid-token");
        assertNull(tracks);
    }

    @Test
    public void testCreateTrack() {
        TrackDTO track = trackService.createTrack(9, "New Track", "New Artist", 300, "New Album", 10, "01-01-2022", "New Description", true);
        assertNotNull(track);
        assertEquals(9, track.getId());
        assertEquals("New Track", track.getTitle());
        assertEquals("New Artist", track.getPerformer());
        assertEquals(300, track.getDuration());
        assertEquals("New Album", track.getAlbum());
        assertEquals(10, track.getPlaycount());
        assertEquals("01-01-2022", track.getPublicationDate());
        assertEquals("New Description", track.getDescription());
        assertTrue(track.isOfflineAvailable());
    }
}