package nl.nusayba.oose.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String performer;
    private int duration;
    private String album;
    private Integer playcount;
    private LocalDate publicationDate;
    private String description;
    private boolean offlineAvailable;

    @ManyToMany(mappedBy = "tracks")
    private List<Playlist> playlists;

    public Track() {}

    // Getters en setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPerformer() { return performer; }
    public void setPerformer(String performer) { this.performer = performer; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public Integer getPlaycount() { return playcount; }
    public void setPlaycount(Integer playcount) { this.playcount = playcount; }

    public LocalDate getPublicationDate() { return publicationDate; }
    public void setPublicationDate(LocalDate publicationDate) { this.publicationDate = publicationDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isOfflineAvailable() { return offlineAvailable; }
    public void setOfflineAvailable(boolean offlineAvailable) { this.offlineAvailable = offlineAvailable; }

    public List<Playlist> getPlaylists() { return playlists; }
    public void setPlaylists(List<Playlist> playlists) { this.playlists = playlists; }
}
