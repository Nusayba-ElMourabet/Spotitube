package nl.nusayba.oose.domain.services;

import jakarta.persistence.EntityManager;
import nl.nusayba.oose.domain.entities.Playlist;
import nl.nusayba.oose.domain.repositories.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlaylistRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private PlaylistRepository playlistRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        // Arrange
        int playlistId = 1;
        Playlist mockPlaylist = new Playlist();
        mockPlaylist.setId(playlistId);
        when(entityManager.find(Playlist.class, playlistId)).thenReturn(mockPlaylist);

        // Act
        Playlist result = playlistRepository.findById(playlistId);

        // Assert
        assertEquals(mockPlaylist, result);
        verify(entityManager, times(1)).find(Playlist.class, playlistId);
    }
}