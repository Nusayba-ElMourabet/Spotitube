package nl.nusayba.oose.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class TracksDTO {
    private List<TrackDTO> tracks = new ArrayList<>();
    private int length;

    public TracksDTO(List<TrackDTO> t) {
        this.tracks = t;
    }

    public TracksDTO (){
    }

    // Getters and Setters
    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(TrackDTO track) {
        tracks.add(track);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
