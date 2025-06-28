package nl.nusayba.oose.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.nusayba.oose.domain.entities.Playlist;

import java.util.List;

public class PlaylistRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Playlist findById(Long id) {
        return entityManager.find(Playlist.class, id);
    }

    public List<Playlist> findAll() {
        return entityManager.createQuery("SELECT p FROM Playlist p", Playlist.class).getResultList();
    }

    @Transactional
    public void save(Playlist playlist) {
        entityManager.persist(playlist);
    }
}
