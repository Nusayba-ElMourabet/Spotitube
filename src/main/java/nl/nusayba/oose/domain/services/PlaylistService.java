package nl.nusayba.oose.domain.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.interfaces.IPlaylistDAO;

import java.util.List;

@ApplicationScoped
public class PlaylistService {

    @Inject
    private IPlaylistDAO playlistDAO;

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public PlaylistDTO getPlaylistById(int id) {
        return playlistDAO.getPlaylistById(id);
    }

    public void addPlaylist(PlaylistDTO playlist) {
        playlistDAO.insertPlaylist(playlist);
    }

    public void updatePlaylist(PlaylistDTO playlist) {
        playlistDAO.updatePlaylist(playlist);
    }

    public void deletePlaylist(int id) {
        playlistDAO.deletePlaylist(id);
    }
}