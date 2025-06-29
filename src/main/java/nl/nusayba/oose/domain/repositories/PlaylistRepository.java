package nl.nusayba.oose.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.entities.Playlist;
import nl.nusayba.oose.domain.entities.Users; // Import Users

import java.util.List;

// Add ApplicationScoped for CDI injection
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // Make it a CDI bean
public class PlaylistRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Playlist> findAllWithOwner() {
        String jpql = "SELECT p FROM Playlist p JOIN FETCH p.owner";
        TypedQuery<Playlist> query = entityManager.createQuery(jpql, Playlist.class);
        return query.getResultList();
    }















    public Playlist findById(int id) {
        return entityManager.find(Playlist.class, id);
    }

    public List<Playlist> findAll() {
        return entityManager.createQuery("SELECT p FROM Playlist p", Playlist.class).getResultList();
    }

    // New method to find playlists by owner
    public List<Playlist> findByOwner(Users owner) {
        return entityManager.createQuery("SELECT p FROM Playlist p WHERE p.owner = :owner", Playlist.class)
                .setParameter("owner", owner)
                .getResultList();
    }

    @Transactional
    public void save(Playlist playlist) {
        entityManager.persist(playlist);
    }

    @Transactional
    public void delete(Long id) {
        Playlist playlist = entityManager.find(Playlist.class, id);
        if (playlist != null) {
            entityManager.remove(playlist);
        }
    }

    @Transactional
    public void update(Playlist playlist) {
        entityManager.merge(playlist);
    }
}
