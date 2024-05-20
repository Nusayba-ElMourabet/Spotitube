package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




public class PlaylistServiceTest {

    private PlaylistService playlistService;

    @BeforeEach
    public void setUp() {
        playlistService = new PlaylistService();
    }

    @Test
    public void testGetPlaylistsWithValidToken() {
        List<PlaylistDTO> playlists = playlistService.getPlaylists("dummy-token");
        assertNotNull(playlists);
        assertEquals(4, playlists.size());

        PlaylistDTO firstPlaylist = playlists.get(0);
        assertEquals(1, firstPlaylist.getId());
        assertEquals("Heavy Metal", firstPlaylist.getName());
        assertTrue(firstPlaylist.isOwner());

        PlaylistDTO secondPlaylist = playlists.get(1);
        assertEquals(2, secondPlaylist.getId());
        assertEquals("Pop", secondPlaylist.getName());
        assertFalse(secondPlaylist.isOwner());

        PlaylistDTO thirdPlaylist = playlists.get(2);
        assertEquals(3, thirdPlaylist.getId());
        assertEquals("Jazz", thirdPlaylist.getName());
        assertTrue(thirdPlaylist.isOwner());

        PlaylistDTO fourthPlaylist = playlists.get(3);
        assertEquals(4, fourthPlaylist.getId());
        assertEquals("Classical", fourthPlaylist.getName());
        assertFalse(fourthPlaylist.isOwner());
    }

    @Test
    public void testGetPlaylistsWithInvalidToken() {
        List<PlaylistDTO> playlists = playlistService.getPlaylists("invalid-token");
        assertNull(playlists);
    }
}
