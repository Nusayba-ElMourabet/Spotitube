package nl.nusayba.oose.domain.services;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private static final String HARDCODED_TOKEN = "dummy-token";
    private List<PlaylistDTO> playlists;
    private TrackService trackService;

    public PlaylistService(TrackService trackService) {
        this.trackService = trackService;
        // Initialize hardcoded playlists
        this.playlists = new ArrayList<>();

        PlaylistDTO playlist1 = createPlaylist(1, "Heavy Metal", true);
        playlist1.setTracks(getTracksForPlaylist(1));
        this.playlists.add(playlist1);

        PlaylistDTO playlist2 = createPlaylist(2, "Pop", false);
        playlist2.setTracks(getTracksForPlaylist(2));
        this.playlists.add(playlist2);

        PlaylistDTO playlist3 = createPlaylist(3, "Jazz", true);
        playlist3.setTracks(getTracksForPlaylist(3));
        this.playlists.add(playlist3);

        PlaylistDTO playlist4 = createPlaylist(4, "Classical", false);
        playlist4.setTracks(getTracksForPlaylist(4));
        this.playlists.add(playlist4);
    }

    public List<PlaylistDTO> getPlaylists(String token) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        return playlists;
    }

    public PlaylistDTO getPlaylistById(String token, int playlistId) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        return playlists.stream().filter(playlist -> playlist.getId() == playlistId).findFirst().orElse(null);
    }

    private PlaylistDTO createPlaylist(int id, String name, boolean owner) {
        PlaylistDTO playlist = new PlaylistDTO();
        playlist.setId(id);
        playlist.setName(name);
        playlist.setOwner(owner);
        return playlist;
    }

    private List<TrackDTO> getTracksForPlaylist(int playlistId) {
        List<TrackDTO> allTracks = trackService.getTracks(HARDCODED_TOKEN);
        if (allTracks == null) {
            return new ArrayList<>();
        }

        List<TrackDTO> playlistTracks = new ArrayList<>();
        for (TrackDTO track : allTracks) {
            if ((playlistId == 1 && (track.getId() == 1 || track.getId() == 2)) ||
                    (playlistId == 2 && (track.getId() == 3 || track.getId() == 4)) ||
                    (playlistId == 3 && (track.getId() == 5 || track.getId() == 6)) ||
                    (playlistId == 4 && (track.getId() == 7 || track.getId() == 8))) {
                playlistTracks.add(track);
            }
        }
        return playlistTracks;
    }
}