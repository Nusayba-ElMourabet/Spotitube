package nl.nusayba.oose.domain.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.nusayba.oose.datasource.PlaylistDAO;
import nl.nusayba.oose.datasource.TrackDAO;
import nl.nusayba.oose.domain.dto.PlaylistDTO;

import java.util.List;

@ApplicationScoped
public class PlaylistService {

    private static final String HARDCODED_TOKEN = "dummy-token";

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TrackDAO trackDAO;

    // No-argument constructor for CDI
    public PlaylistService() {
    }

    public List<PlaylistDTO> getPlaylists(String token) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        return playlistDAO.getPlaylists();
    }

    public PlaylistDTO getPlaylistById(String token, int playlistId) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        PlaylistDTO playlist = playlistDAO.getPlaylistById(playlistId);
        if (playlist != null) {
            playlist.setTracks(trackDAO.getTracks());
        }
        return playlist;
    }
}