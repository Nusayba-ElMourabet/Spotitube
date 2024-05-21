package nl.nusayba.oose.datasource;


import jakarta.enterprise.context.ApplicationScoped;
import nl.nusayba.oose.domain.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlaylistDAO {

    private List<PlaylistDTO> playlists;

    public PlaylistDAO() {
        playlists = new ArrayList<>();
        PlaylistDTO playlist1 = new PlaylistDTO();
        playlist1.setId(1);
        playlist1.setName("Heavy Metal");
        playlist1.setOwner(true);
        playlist1.setTracks(new ArrayList<>()); // Initialize with empty track list

        PlaylistDTO playlist2 = new PlaylistDTO();
        playlist2.setId(2);
        playlist2.setName("Pop");
        playlist2.setOwner(false);
        playlist2.setTracks(new ArrayList<>()); // Initialize with empty track list

        playlists.add(playlist1);
        playlists.add(playlist2);
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public PlaylistDTO getPlaylistById(int playlistId) {
        return playlists.stream().filter(playlist -> playlist.getId() == playlistId).findFirst().orElse(null);
    }
}