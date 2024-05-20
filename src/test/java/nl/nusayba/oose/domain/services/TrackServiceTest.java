package nl.nusayba.oose.domain.services;

import  nl.nusayba.oose.domain.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrackServiceTest {

    private TrackService trackService;

    @BeforeEach
    public void setUp() {
        trackService = new TrackService();
    }

    @Test
    public void testGetTracksWithValidToken() {
        List<TrackDTO> tracks = trackService.getTracks("dummy-token");
        assertNotNull(tracks);
        assertEquals(8, tracks.size());

        TrackDTO firstTrack = tracks.get(0);
        assertEquals(1, firstTrack.getId());
        assertEquals("Master of Puppets", firstTrack.getTitle());
        assertEquals("The Frames", firstTrack.getPerformer());

        TrackDTO secondTrack = tracks.get(1);
        assertEquals(2, secondTrack.getId());
        assertEquals("The cost", secondTrack.getTitle());
        assertEquals("The Frames", secondTrack.getPerformer());
    }

    @Test
    public void testGetTracksWithInvalidToken() {
        List<TrackDTO> tracks = trackService.getTracks("invalid-token");
        assertNull(tracks);
    }
}
