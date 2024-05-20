package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;


public class TrackService {
    private static final String HARDCODED_TOKEN = "dummy-token";
    private List<TrackDTO> tracks;

    public TrackService() {
        // Initialize hardcoded tracks
        this.tracks = new ArrayList<>();
        this.tracks.add(createTrack(1, "Master of Puppets", "Metallica", 515, "Master of Puppets", 0, null, null, false));
        this.tracks.add(createTrack(2, "Back in Black", "AC/DC", 255, "Back in Black", 37, "25-07-1980", "Classic rock song", true));
        this.tracks.add(createTrack(3, "Thriller", "Michael Jackson", 357, "Thriller", 0, null, null, false));
        this.tracks.add(createTrack(4, "Like a Prayer", "Madonna", 345, "Like a Prayer", 50, "21-03-1989", "Pop song", true));
        this.tracks.add(createTrack(5, "So What", "Miles Davis", 562, "Kind of Blue", 0, null, null, false));
        this.tracks.add(createTrack(6, "Take Five", "Dave Brubeck", 324, "Time Out", 20, "25-06-1959", "Jazz standard", true));
        this.tracks.add(createTrack(7, "Fur Elise", "Ludwig van Beethoven", 180, null, 0, null, null, false));
        this.tracks.add(createTrack(8, "The Four Seasons", "Antonio Vivaldi", 2520, "The Four Seasons", 100, "1723", "Classical masterpiece", true));
    }

    public List<TrackDTO> getTracks(String token) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        return tracks;
    }

    private TrackDTO createTrack(int id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, boolean offlineAvailable) {
        TrackDTO track = new TrackDTO();
        track.setId(id);
        track.setTitle(title);
        track.setPerformer(performer);
        track.setDuration(duration);
        track.setAlbum(album);
        track.setPlaycount(playcount);
        track.setPublicationDate(publicationDate);
        track.setDescription(description);
        track.setOfflineAvailable(offlineAvailable);
        return track;
    }
}