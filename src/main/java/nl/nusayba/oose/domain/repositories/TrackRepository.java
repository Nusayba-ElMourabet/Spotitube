package nl.nusayba.oose.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.nusayba.oose.domain.entities.Track;

import java.util.List;

public class TrackRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Track findById(Long id) {
        return entityManager.find(Track.class, id);
    }

    public List<Track> findAll() {
        return entityManager.createQuery("SELECT t FROM Track t", Track.class).getResultList();
    }

    @Transactional
    public void save(Track track) {
        entityManager.persist(track);
    }
}
