package nl.nusayba.oose.util;

import jakarta.persistence.EntityManager;
import nl.nusayba.oose.domain.entities.Playlist;

import java.util.List;

public class TestJPA {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            List<Playlist> playlists = em.createQuery("SELECT p FROM Playlist p WHERE p.name = 'Heartbreak'", Playlist.class).getResultList();
            for (Playlist playlist : playlists) {
                System.out.println("Gebruiker: " + playlist.getName());
            }
        } finally {
            em.close();
            JPAUtil.shutdown();
        }
    }
}