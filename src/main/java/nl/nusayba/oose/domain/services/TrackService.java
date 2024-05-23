package nl.nusayba.oose.domain.services;

import nl.nusayba.oose.datasource.LoginDAO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.interfaces.ITrackDAO;

import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TrackService {

    private ITrackDAO trackDAO;

    @Inject
    public void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public TrackDTO getTrackById(int id) {
        return trackDAO.getTrackById(id);
    }

    public TracksDTO addTrack(TrackDTO trackDTO) {
        trackDAO.addTrack(trackDTO);
        return trackDAO.getAllTracks();
    }

    public TracksDTO deleteTrack(int id) {
        trackDAO.deleteTrack(id);
        return trackDAO.getAllTracks();
    }

    public TracksDTO updateTrack(int id, TrackDTO trackDTO) {
        trackDAO.updateTrack(id, trackDTO);
        return trackDAO.getAllTracks();
    }
}
