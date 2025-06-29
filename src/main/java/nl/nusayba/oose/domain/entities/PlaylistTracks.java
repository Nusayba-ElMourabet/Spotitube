package nl.nusayba.oose.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PlaylistTracks")
public class PlaylistTracks {

    @EmbeddedId
    private PlaylistTrackId id;

    @ManyToOne
    @MapsId("playlistId")
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne
    @MapsId("trackId")
    @JoinColumn(name = "track_id")
    private Track track;

    @Column(name = "offline_available")
    private boolean offlineAvailable;

    public PlaylistTracks() {
    }

    public PlaylistTracks(Playlist playlist, Track track, boolean offlineAvailable) {
        this.playlist = playlist;
        this.track = track;
        this.offlineAvailable = offlineAvailable;
        this.id = new PlaylistTrackId(playlist.getId(), track.getId());
    }

    public PlaylistTrackId getId() {
        return id;
    }

    public void setId(PlaylistTrackId id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
