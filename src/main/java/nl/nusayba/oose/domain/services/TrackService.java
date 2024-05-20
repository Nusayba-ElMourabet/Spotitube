package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.domain.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;


public class TrackService {

    private static final String HARDCODED_TOKEN = "dummy-token";
    private List<TrackDTO> tracks;

    public TrackService() {
        tracks = new ArrayList<>();
        tracks.add(createTrack(1, "Master of Puppets", "Metallica", 515, "Master of Puppets", 0, null, null, false));
        tracks.add(createTrack(2, "Back in Black", "AC/DC", 255, "Back in Black", 37, "25-07-1980", "Classic rock song", true));
        tracks.add(createTrack(3, "Thriller", "Michael Jackson", 357, "Thriller", 0, null, null, false));
        tracks.add(createTrack(4, "Like a Prayer", "Madonna", 345, "Like a Prayer", 50, "21-03-1989", "Pop song", true));
        tracks.add(createTrack(5, "So What", "Miles Davis", 540, "Kind of Blue", 0, null, null, false));
        tracks.add(createTrack(6, "Take Five", "Dave Brubeck", 324, "Time Out", 0, null, null, false));
        tracks.add(createTrack(7, "Fur Elise", "Beethoven", 240, "Classical Hits", 0, null, null, false));
        tracks.add(createTrack(8, "The Four Seasons", "Vivaldi", 2520, "The Four Seasons", 0, null, null, false));
    }

    public List<TrackDTO> getTracks(String token) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        return tracks;
    }

    public TrackDTO createTrack(int id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, boolean offlineAvailable) {
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