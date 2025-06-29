package nl.nusayba.oose.domain.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlaylistTrackId implements Serializable {

    private int playlistId;
    private int trackId;

    public PlaylistTrackId() {
    }

    public PlaylistTrackId(int playlistId, int trackId) {
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistTrackId)) return false;
        PlaylistTrackId that = (PlaylistTrackId) o;
        return playlistId == that.playlistId && trackId == that.trackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, trackId);
    }
}
