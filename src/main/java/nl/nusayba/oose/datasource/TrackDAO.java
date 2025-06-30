package nl.nusayba.oose.datasource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.entities.Playlist;
import nl.nusayba.oose.domain.entities.Track;
import nl.nusayba.oose.domain.interfaces.ITrackDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class TrackDAO implements ITrackDAO {

    private Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext(unitName = "spotitubePU")
    private EntityManager entityManager;

    @Override
    public TracksDTO getAllTracksinPlaylist(int playlistId) {
        TracksDTO tracksDTO = new TracksDTO();
        try {
            Playlist playlist = entityManager.find(Playlist.class, playlistId);
            if (playlist != null) {
                List<TrackDTO> trackDTOs = new ArrayList<>();
                int totalDuration = 0;
                for (Track track : playlist.getTracks()) {
                    TrackDTO dto = mapToDTO(track);
                    trackDTOs.add(dto);
                    totalDuration += track.getDuration();
                }
                tracksDTO.setTracks(trackDTOs);
                tracksDTO.setLength(totalDuration);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching tracks in playlist ID: " + playlistId, e);
        }
        return tracksDTO;
    }

    @Override
    public TracksDTO getAllTracks() {
        TracksDTO tracksDTO = new TracksDTO();
        try {
            List<Track> tracks = entityManager.createQuery("SELECT t FROM Track t", Track.class).getResultList();
            List<TrackDTO> trackDTOs = new ArrayList<>();
            int totalDuration = 0;

            for (Track track : tracks) {
                TrackDTO dto = mapToDTO(track);
                trackDTOs.add(dto);
                totalDuration += track.getDuration();
            }
            tracksDTO.setTracks(trackDTOs);
            tracksDTO.setLength(totalDuration);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all tracks", e);
        }
        return tracksDTO;
    }

    @Override
    @Transactional
    public void addTrack(TrackDTO trackDTO) {
        try {
            Track track = mapToEntity(trackDTO);
            entityManager.persist(track);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding track", e);
        }
    }

    @Override
    @Transactional
    public void deleteTrack(int id) {
        try {
            Track track = entityManager.find(Track.class, id);
            if (track != null) {
                entityManager.remove(track);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting track with ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void updateTrack(int id, TrackDTO trackDTO) {
        try {
            Track track = entityManager.find(Track.class, id);
            if (track != null) {
                track.setTitle(trackDTO.getTitle());
                track.setPerformer(trackDTO.getPerformer());
                track.setDuration(trackDTO.getDuration());
                track.setAlbum(trackDTO.getAlbum());
                track.setPlaycount(trackDTO.getPlaycount());

                if (trackDTO.getPublicationDate() != null) {
                    track.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(trackDTO.getPublicationDate()));
                }

                track.setDescription(trackDTO.getDescription());
                track.setOfflineAvailable(trackDTO.isOfflineAvailable());
                entityManager.merge(track);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating track with ID: " + id, e);
        }
    }

    @Override
    public TrackDTO getTrackById(int id) {
        TrackDTO dto = new TrackDTO();
        try {
            Track track = entityManager.find(Track.class, id);
            if (track != null) {
                dto = mapToDTO(track);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching track by ID: " + id, e);
        }
        return dto;
    }

    @Override
    public TracksDTO getTracksNotInPlaylist(int playlistId) {
        TracksDTO tracksDTO = new TracksDTO();
        try {
            Playlist playlist = entityManager.find(Playlist.class, playlistId);
            if (playlist != null) {
                List<Track> allTracks = entityManager.createQuery("SELECT t FROM Track t", Track.class).getResultList();
                List<TrackDTO> notInPlaylist = new ArrayList<>();
                int totalDuration = 0;

                for (Track track : allTracks) {
                    if (!playlist.getTracks().contains(track)) {
                        TrackDTO dto = mapToDTO(track);
                        notInPlaylist.add(dto);
                        totalDuration += track.getDuration();
                    }
                }
                tracksDTO.setTracks(notInPlaylist);
                tracksDTO.setLength(totalDuration);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching tracks not in playlist ID: " + playlistId, e);
        }
        return tracksDTO;
    }

    @Override
    @Transactional
    public void addTrackToPlaylist(int playlistId, TrackDTO trackDTO) {
        try {
            Playlist playlist = entityManager.find(Playlist.class, playlistId);
            Track track = entityManager.find(Track.class, trackDTO.getId());

            if (playlist != null && track != null) {
                playlist.getTracks().add(track);
                entityManager.merge(playlist);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding track to playlist ID: " + playlistId, e);
        }
    }

    @Override
    @Transactional
    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        try {
            Playlist playlist = entityManager.find(Playlist.class, playlistId);
            Track track = entityManager.find(Track.class, trackId);

            if (playlist != null && track != null) {
                playlist.getTracks().remove(track);
                entityManager.merge(playlist);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting track from playlist ID: " + playlistId, e);
        }
    }

    private TrackDTO mapToDTO(Track track) {
        TrackDTO dto = new TrackDTO();
        dto.setId(track.getId());
        dto.setTitle(track.getTitle());
        dto.setPerformer(track.getPerformer());
        dto.setDuration(track.getDuration());
        dto.setAlbum(track.getAlbum());
        dto.setPlaycount(track.getPlaycount());

        if (track.getPublicationDate() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dto.setPublicationDate(formatter.format(track.getPublicationDate()));
        }

        dto.setDescription(track.getDescription());
        dto.setOfflineAvailable(track.isOfflineAvailable());
        return dto;
    }

    private Track mapToEntity(TrackDTO dto) throws Exception {
        Track track = new Track();
        track.setTitle(dto.getTitle());
        track.setPerformer(dto.getPerformer());
        track.setDuration(dto.getDuration());
        track.setAlbum(dto.getAlbum());
        track.setPlaycount(dto.getPlaycount());

        if (dto.getPublicationDate() != null) {
            track.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getPublicationDate()));
        }

        track.setDescription(dto.getDescription());
        track.setOfflineAvailable(dto.isOfflineAvailable());
        return track;
    }
}
