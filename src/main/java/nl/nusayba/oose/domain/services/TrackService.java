package nl.nusayba.oose.domain.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.interfaces.ITrackDAO;

import java.util.List;

@ApplicationScoped
public class TrackService {

    @Inject
    private ITrackDAO trackDAO;

    public List<TrackDTO> getAllTracks() {
        return trackDAO.getAllTracks();
    }

    public TrackDTO getTrackById(int id) {
        return trackDAO.getTrackById(id);
    }

    public void addTrack(TrackDTO track) {
        trackDAO.insertTrack(track);
    }

    public void updateTrack(TrackDTO track) {
        trackDAO.updateTrack(track);
    }

    public void deleteTrack(int id) {
        trackDAO.deleteTrack(id);
    }
}
