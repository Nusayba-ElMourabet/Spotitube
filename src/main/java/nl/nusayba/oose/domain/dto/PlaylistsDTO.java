package nl.nusayba.oose.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsDTO {

    private List<PlaylistDTO> playlists = new ArrayList<>();
    private int length;

    public PlaylistsDTO(List<PlaylistDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }
    public PlaylistsDTO() {
        //default
    }
    public int getLength() {
        return length;
    }

    public void addPlaylist(PlaylistDTO playlist) {
        playlists.add(playlist);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public PlaylistDTO getPlaylist(int id) {
        for (PlaylistDTO playlist : playlists) {
            if (playlist.getId() == id) {
                return playlist;
            }
        }
        return null;
    }
    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

}
