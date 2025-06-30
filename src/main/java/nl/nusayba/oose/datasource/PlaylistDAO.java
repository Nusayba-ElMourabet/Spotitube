package nl.nusayba.oose.datasource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.entities.Playlist;
import nl.nusayba.oose.domain.entities.Track;
import nl.nusayba.oose.domain.entities.User;
import nl.nusayba.oose.domain.interfaces.IPlaylistDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PlaylistDAO implements IPlaylistDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext(unitName = "spotitubePU")
    private EntityManager entityManager;

    @Override
    public PlaylistsDTO getPlaylist(String username) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        try {
            List<Playlist> playlists = entityManager.createQuery(
                            "SELECT p FROM Playlist p WHERE p.owner.username = :username", Playlist.class)
                    .setParameter("username", username)
                    .getResultList();

            int totalDuration = 0;

            for (Playlist playlist : playlists) {
                PlaylistDTO dto = new PlaylistDTO();
                dto.setId(playlist.getId());
                dto.setName(playlist.getName());
                dto.setOwner(playlist.getOwner().getUsername().equals(username));

                List<TrackDTO> trackDTOs = new ArrayList<>();
                for (Track track : playlist.getTracks()) {
                    TrackDTO trackDTO = mapToDTO(track);
                    trackDTOs.add(trackDTO);
                    totalDuration += track.getDuration();
                }
                dto.setTracks(trackDTOs);
                playlistsDTO.addPlaylist(dto);
            }

            playlistsDTO.setLength(totalDuration);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching playlists for user: " + username, e);
        }
        return playlistsDTO;
    }

    @Override
    public int calculateTotalDuration() {
        try {
            Long total = entityManager.createQuery(
                            "SELECT SUM(t.duration) FROM Track t", Long.class)
                    .getSingleResult();
            return total != null ? total.intValue() : 0;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error calculating total duration", e);
            return 0;
        }
    }

    @Override
    @Transactional
    public void updatePlaylist(int id, PlaylistDTO playlistDTO) {
        try {
            Playlist playlist = entityManager.find(Playlist.class, id);
            if (playlist != null) {
                playlist.setName(playlistDTO.getName());
                entityManager.merge(playlist);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating playlist with ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void addPlaylist(String username, PlaylistDTO playlistDTO) {
        try {
            User user = entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            Playlist playlist = new Playlist();
            playlist.setName(playlistDTO.getName());
            playlist.setOwner(user);
            entityManager.persist(playlist);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding playlist for user: " + username, e);
        }
    }

    @Override
    @Transactional
    public void addTrackToPlaylist(int playlistId, int trackId) {
        try {
            Playlist playlist = entityManager.find(Playlist.class, playlistId);
            Track track = entityManager.find(Track.class, trackId);
            if (playlist != null && track != null) {
                playlist.getTracks().add(track);
                entityManager.merge(playlist);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding track to playlist", e);
        }
    }

    @Override
    @Transactional
    public void deletePlaylist(int id) {
        try {
            Playlist playlist = entityManager.find(Playlist.class, id);
            if (playlist != null) {
                entityManager.remove(playlist);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting playlist with ID: " + id, e);
        }
    }

    @Override
    public PlaylistDTO getPlaylistById(int id) {
        PlaylistDTO dto = new PlaylistDTO();
        try {
            Playlist playlist = entityManager.find(Playlist.class, id);
            if (playlist != null) {
                dto.setId(playlist.getId());
                dto.setName(playlist.getName());

                List<TrackDTO> trackDTOs = new ArrayList<>();
                for (Track track : playlist.getTracks()) {
                    trackDTOs.add(mapToDTO(track));
                }
                dto.setTracks(trackDTOs);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching playlist by ID: " + id, e);
        }
        return dto;
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
            String formattedDate = formatter.format(track.getPublicationDate());
            dto.setPublicationDate(formattedDate);
        }        dto.setDescription(track.getDescription());
        dto.setOfflineAvailable(track.isOfflineAvailable());
        return dto;
    }
}