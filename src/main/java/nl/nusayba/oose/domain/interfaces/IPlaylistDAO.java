package nl.nusayba.oose.domain.interfaces;

import  nl.nusayba.oose.domain.dto.PlaylistDTO;

import java.util.List;

public interface IPlaylistDAO {
    List<PlaylistDTO> getAllPlaylists();
    PlaylistDTO getPlaylistById(int id);
    void insertPlaylist(PlaylistDTO playlist);
    void updatePlaylist(PlaylistDTO playlist);
    void deletePlaylist(int id);
}
