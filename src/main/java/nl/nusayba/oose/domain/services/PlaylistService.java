package nl.nusayba.oose.domain.services;
import nl.nusayba.oose.domain.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private static final String HARDCODED_TOKEN = "dummy-token";
    private List<PlaylistDTO> playlists;

    public PlaylistService() {
        // hardcoded playlists
        this.playlists = new ArrayList<>();
        this.playlists.add(createPlaylist(1, "Heavy Metal", true));
        this.playlists.add(createPlaylist(2, "Pop", false));
        this.playlists.add(createPlaylist(3, "Jazz", true));
        this.playlists.add(createPlaylist(4, "Classical", false));
    }

    public List<PlaylistDTO> getPlaylists(String token) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        return playlists;
    }

    private PlaylistDTO createPlaylist(int id, String name, boolean owner) {
        PlaylistDTO playlist = new PlaylistDTO();
        playlist.setId(id);
        playlist.setName(name);
        playlist.setOwner(owner);
        return playlist;
    }
}

