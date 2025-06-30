package nl.nusayba.oose.domain.interfaces;

import jakarta.transaction.Transactional;
import  nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;



public interface IPlaylistDAO {
    PlaylistsDTO getPlaylist(String username);
    void addPlaylist(String username, PlaylistDTO playlistDTO);

    @Transactional
    void addTrackToPlaylist(int playlistId, int trackId);

    void deletePlaylist(int id);
    void updatePlaylist(int id, PlaylistDTO playlistDTO);
    int calculateTotalDuration();

    PlaylistDTO getPlaylistById(int id);
}
