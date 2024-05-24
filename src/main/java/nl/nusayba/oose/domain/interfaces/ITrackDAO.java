package nl.nusayba.oose.domain.interfaces;

import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;

import java.util.List;

public interface ITrackDAO {
    TracksDTO getAllTracksinPlaylist(int playlistId);

    TracksDTO getAllTracks();

    void addTrack(TrackDTO trackDTO);

    void deleteTrack(int id);

    void updateTrack(int id, TrackDTO trackDTO);

    TrackDTO getTrackById(int id);

    //    void addTrackToPlaylist(int trackId, int playlistId);
    TracksDTO getTracksNotInPlaylist(int playlistId);

    void addTrackToPlaylist(int id, TrackDTO trackDTO);

    void deleteTrackFromPlaylist(int id, int trackId);
}
