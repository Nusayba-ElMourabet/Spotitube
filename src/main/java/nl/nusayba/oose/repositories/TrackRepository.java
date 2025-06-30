package nl.nusayba.oose.repositories;

import nl.nusayba.oose.domain.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Integer> {
    // Hier kun je later extra query-methodes toevoegen
}
