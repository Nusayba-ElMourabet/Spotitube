package nl.nusayba.oose.domain.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nl.nusayba.oose.domain.entities.PlaylistTracks;
import nl.nusayba.oose.domain.entities.PlaylistTrackId;

import java.util.List;

@ApplicationScoped
public class PlaylistTracksRepository {

    @PersistenceContext
    private EntityManager em;

    public List<PlaylistTracks> findAll() {
        return em.createQuery("SELECT pt FROM PlaylistTracks pt", PlaylistTracks.class).getResultList();
    }

    public List<PlaylistTracks> findByPlaylistId(int playlistId) {
        return em.createQuery("SELECT pt FROM PlaylistTracks pt WHERE pt.id.playlistId = :playlistId", PlaylistTracks.class)
                .setParameter("playlistId", playlistId)
                .getResultList();
    }

    public void add(PlaylistTracks playlistTrack) {
        em.persist(playlistTrack);
    }

    public void deleteById(PlaylistTrackId id) {
        PlaylistTracks playlistTrack = em.find(PlaylistTracks.class, id);
        if (playlistTrack != null) {
            em.remove(playlistTrack);
        }
    }
}
