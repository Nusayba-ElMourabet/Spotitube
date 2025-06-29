package nl.nusayba.oose.domain.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nl.nusayba.oose.domain.entities.Playlist;
import nl.nusayba.oose.domain.entities.Track;

import java.util.List;

@ApplicationScoped
public class TrackRepository {

    @PersistenceContext(unitName = "spotitubePU")
    private EntityManager em;

    public List<Track> findTracksInPlaylist(int playlistId) {
        return em.createQuery(
                        "SELECT t FROM Track t JOIN t.playlists p WHERE p.id = :playlistId", Track.class)
                .setParameter("playlistId", (long) playlistId)
                .getResultList();
    }

    public List<Track> findTracksNotInPlaylist(int playlistId) {
        return em.createQuery(
                        "SELECT t FROM Track t WHERE t.id NOT IN (SELECT t2.id FROM Track t2 JOIN t2.playlists p WHERE p.id = :playlistId)", Track.class)
                .setParameter("playlistId", (long) playlistId)
                .getResultList();
    }

    public void addTrackToPlaylist(int playlistId, Track track) {
        // Eerst de playlist ophalen en dan toevoegen aan de relatie
        Playlist playlist = em.find(Playlist.class, (long) playlistId);
        if (playlist != null) {
            playlist.getTracks().add(track);
            em.merge(playlist);
        }
    }

    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        Playlist playlist = em.find(Playlist.class, (long) playlistId);
        if (playlist != null) {
            playlist.getTracks().removeIf(t -> t.getId() == trackId);
            em.merge(playlist);
        }
    }
}
