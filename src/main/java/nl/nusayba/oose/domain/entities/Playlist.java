package nl.nusayba.oose.domain.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner;

    @ManyToMany
    @JoinTable(
            name = "PlaylistTracks",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private List<Track> tracks;

    public Playlist() {}

    // Getters en setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Users getOwner() { return owner; }
    public void setOwner(Users owner) { this.owner = owner; }

    public List<Track> getTracks() { return tracks; }
    public void setTracks(List<Track> tracks) { this.tracks = tracks; }
}
