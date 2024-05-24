package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.exceptions.AuthenticationException;
import nl.nusayba.oose.domain.interfaces.IPlaylistDAO;
import nl.nusayba.oose.domain.interfaces.ILoginDAO;

import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;
import nl.nusayba.oose.domain.interfaces.ITrackDAO;

import java.util.List;

@RequestScoped
public class PlaylistService {

    private IPlaylistDAO playlistDAO;
    private ITrackDAO trackDAO;
    private ILoginDAO loginDAO;

    @Inject
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    public void setLoginDAO(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Inject
    public void setTrackDAO(ITrackDAO trackDAO){this.trackDAO = trackDAO;}

    public PlaylistsDTO getAllPlaylists(String token) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            return playlistDAO.getPlaylist(username);
        }
        throw new AuthenticationException();
    }

    public PlaylistsDTO addPlaylist(String token, PlaylistDTO playlistDTO) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            playlistDAO.addPlaylist(username, playlistDTO);
            return playlistDAO.getPlaylist(username);
        }
        throw new AuthenticationException();
    }


    public PlaylistsDTO deletePlaylist(String token, int id) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            playlistDAO.deletePlaylist(id);
            return playlistDAO.getPlaylist(username);
        } else {
            throw new AuthenticationException();
        }
    }

    public PlaylistsDTO updatePlaylist(String token, int id, PlaylistDTO playlistDTO) {
        if (isValidToken(token)) {
            String username = loginDAO.getUserByToken(token).getUser();
            playlistDAO.updatePlaylist(id, playlistDTO);
            return playlistDAO.getPlaylist(username);
        } else {
            throw new AuthenticationException();
        }
    }

    public TracksDTO getTracksInPlaylist(int playlistId, String token) {
        if (isValidToken(token)) {
            return trackDAO.getAllTracksinPlaylist(playlistId);
        }
        throw new AuthenticationException();
    }

    public TracksDTO getTracksNotInPlaylist(int playlistId, String token) {
        if (isValidToken(token)) {
            return trackDAO.getTracksNotInPlaylist(playlistId);
        }
        throw new AuthenticationException();
    }

    private boolean isValidToken(String token) {
        return loginDAO.getUserByToken(token) != null;
    }

    public TracksDTO addTrackToPlaylist(String token, int id, TrackDTO trackDTO) {
        if (isValidToken(token)) {
            trackDAO.addTrackToPlaylist(id, trackDTO);
            return trackDAO.getAllTracksinPlaylist(id);
        }
        throw new AuthenticationException();
    }

    public TracksDTO deleteTrackFromPlaylist(String token, int id, int trackId) {
        if (isValidToken(token)) {
            trackDAO.deleteTrackFromPlaylist(id, trackId);
            return trackDAO.getAllTracksinPlaylist(id);
        }
        throw new AuthenticationException();
    }
}
