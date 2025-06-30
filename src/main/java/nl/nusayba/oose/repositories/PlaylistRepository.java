package nl.nusayba.oose.repositories;

import nl.nusayba.oose.domain.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    List<Playlist> findByOwnerUsername(String username);
}
