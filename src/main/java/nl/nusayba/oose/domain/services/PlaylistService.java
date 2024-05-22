package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.interfaces.IPlaylistDAO;
import nl.nusayba.oose.domain.interfaces.ILoginDAO;

import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PlaylistService {

    private IPlaylistDAO playlistDAO;
    private ILoginDAO loginDAO;

    @Inject
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    public void setLoginDAO(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
    public PlaylistDTO getPlaylistById(int id) {
        return playlistDAO.getPlaylistById(id);
    }
    public PlaylistsDTO getAllPlaylists(String token) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            return playlistDAO.getPlaylist(username);
        }
        throw new RuntimeException("Invalid token");
    }

    public PlaylistsDTO addPlaylist(String token, PlaylistDTO playlistDTO) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            playlistDAO.addPlaylist(username, playlistDTO);
            return playlistDAO.getPlaylist(username);
        }
        throw new RuntimeException("Invalid token");
    }

    public PlaylistsDTO deletePlaylist(String token, int id) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            PlaylistDTO playlist = playlistDAO.getPlaylistById(id);
            if (playlist.isOwner() && username.equals(playlist.getName())) {
                playlistDAO.deletePlaylist(id);
                return playlistDAO.getPlaylist(username);
            } else {
                throw new RuntimeException("You do not have permission to delete this playlist");
            }
        }
        throw new RuntimeException("Invalid token");
    }

    public PlaylistsDTO updatePlaylist(String token, int id, PlaylistDTO playlistDTO) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            PlaylistDTO existingPlaylist = playlistDAO.getPlaylistById(id);
            if (existingPlaylist.isOwner() && username.equals(existingPlaylist.getName())) {
                playlistDAO.updatePlaylist(id, playlistDTO);
                return playlistDAO.getPlaylist(username);
            } else {
                throw new RuntimeException("You do not have permission to update this playlist");
            }
        }
        throw new RuntimeException("Invalid token");
    }

    private boolean isValidToken(String token) {
        return loginDAO.getUserByToken(token) != null;
    }
}
