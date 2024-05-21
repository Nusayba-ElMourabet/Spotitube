package nl.nusayba.oose.domain.services;

import jakarta.inject.Inject;
import nl.nusayba.oose.datasource.TrackDAO;
import nl.nusayba.oose.domain.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;


public class TrackService {

    private static final String HARDCODED_TOKEN = "dummy-token";

    @Inject
    private TrackDAO trackDAO;

    public List<TrackDTO> getTracks(String token) {
        if (!HARDCODED_TOKEN.equals(token)) {
            return null;
        }
        return trackDAO.getTracks();
    }
}