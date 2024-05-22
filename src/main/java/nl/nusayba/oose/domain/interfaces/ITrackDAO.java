package nl.nusayba.oose.domain.interfaces;

import nl.nusayba.oose.domain.dto.TrackDTO;

import java.util.List;

public interface ITrackDAO {
    List<TrackDTO> getAllTracks();
    TrackDTO getTrackById(int id);
    void insertTrack(TrackDTO track);
    void updateTrack(TrackDTO track);
    void deleteTrack(int id);
}