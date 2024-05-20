package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PlaylistServiceTest {

    private PlaylistService playlistService;

    @BeforeEach
    public void setUp() {
        TrackService trackService = new TrackService();
        playlistService = new PlaylistService(trackService);
    }

    @Test
    public void testGetPlaylistsWithValidToken() {
        List<PlaylistDTO> playlists = playlistService.getPlaylists("dummy-token");
        assertNotNull(playlists);
        assertEquals(4, playlists.size());

        // Test first playlist
        PlaylistDTO firstPlaylist = playlists.get(0);
        assertEquals(1, firstPlaylist.getId());
        assertEquals("Heavy Metal", firstPlaylist.getName());
        assertTrue(firstPlaylist.isOwner());
        List<TrackDTO> firstPlaylistTracks = firstPlaylist.getTracks();
        assertEquals(2, firstPlaylistTracks.size());
        assertEquals(1, firstPlaylistTracks.get(0).getId());
        assertEquals("Master of Puppets", firstPlaylistTracks.get(0).getTitle());
        assertEquals(2, firstPlaylistTracks.get(1).getId());
        assertEquals("Back in Black", firstPlaylistTracks.get(1).getTitle());

        // Test second playlist
        PlaylistDTO secondPlaylist = playlists.get(1);
        assertEquals(2, secondPlaylist.getId());
        assertEquals("Pop", secondPlaylist.getName());
        assertFalse(secondPlaylist.isOwner());
        List<TrackDTO> secondPlaylistTracks = secondPlaylist.getTracks();
        assertEquals(2, secondPlaylistTracks.size());
        assertEquals(3, secondPlaylistTracks.get(0).getId());
        assertEquals("Thriller", secondPlaylistTracks.get(0).getTitle());
        assertEquals(4, secondPlaylistTracks.get(1).getId());
        assertEquals("Like a Prayer", secondPlaylistTracks.get(1).getTitle());

        // Test third playlist
        PlaylistDTO thirdPlaylist = playlists.get(2);
        assertEquals(3, thirdPlaylist.getId());
        assertEquals("Jazz", thirdPlaylist.getName());
        assertTrue(thirdPlaylist.isOwner());
        List<TrackDTO> thirdPlaylistTracks = thirdPlaylist.getTracks();
        assertEquals(2, thirdPlaylistTracks.size());
        assertEquals(5, thirdPlaylistTracks.get(0).getId());
        assertEquals("So What", thirdPlaylistTracks.get(0).getTitle());
        assertEquals(6, thirdPlaylistTracks.get(1).getId());
        assertEquals("Take Five", thirdPlaylistTracks.get(1).getTitle());

        // Test fourth playlist
        PlaylistDTO fourthPlaylist = playlists.get(3);
        assertEquals(4, fourthPlaylist.getId());
        assertEquals("Classical", fourthPlaylist.getName());
        assertFalse(fourthPlaylist.isOwner());
        List<TrackDTO> fourthPlaylistTracks = fourthPlaylist.getTracks();
        assertEquals(2, fourthPlaylistTracks.size());
        assertEquals(7, fourthPlaylistTracks.get(0).getId());
        assertEquals("Fur Elise", fourthPlaylistTracks.get(0).getTitle());
        assertEquals(8, fourthPlaylistTracks.get(1).getId());
        assertEquals("The Four Seasons", fourthPlaylistTracks.get(1).getTitle());
    }

    @Test
    public void testGetPlaylistsWithInvalidToken() {
        List<PlaylistDTO> playlists = playlistService.getPlaylists("invalid-token");
        assertNull(playlists);
    }

    @Test
    public void testGetPlaylistByIdWithValidToken() {
        PlaylistDTO playlist = playlistService.getPlaylistById("dummy-token", 1);
        assertNotNull(playlist);
        assertEquals(1, playlist.getId());
        assertEquals("Heavy Metal", playlist.getName());
        List<TrackDTO> tracks = playlist.getTracks();
        assertEquals(2, tracks.size());
        assertEquals(1, tracks.get(0).getId());
        assertEquals("Master of Puppets", tracks.get(0).getTitle());
        assertEquals(2, tracks.get(1).getId());
        assertEquals("Back in Black", tracks.get(1).getTitle());
    }

    @Test
    public void testGetPlaylistByIdWithInvalidToken() {
        PlaylistDTO playlist = playlistService.getPlaylistById("invalid-token", 1);
        assertNull(playlist);
    }

    @Test
    public void testGetPlaylistByIdWithInvalidId() {
        PlaylistDTO playlist = playlistService.getPlaylistById("dummy-token", 999);
        assertNull(playlist);
    }
}