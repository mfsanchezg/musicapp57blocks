import io._57blocks.music.dto.GenericResponse;
import io._57blocks.music.dto.SongRequest;
import io._57blocks.music.entities.Song;
import io._57blocks.music.entities.User;
import io._57blocks.music.exceptions.MusicAppException;
import io._57blocks.music.repositories.SongRepository;
import io._57blocks.music.services.SongService;
import io._57blocks.music.utils.SongMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private SongMapper songMapper;

    @InjectMocks
    private SongService songService;

    private User user;
    private SongRequest songRequest;
    private Song song;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setEmail("test@test.com");

        songRequest = new SongRequest();
        songRequest.setId(1);
        songRequest.setName("testSong");
        songRequest.setArtist("testArtist");

        song = new Song();
        song.setId(1);
        song.setName("testSong");
        song.setArtist("testArtist");
        song.setUser(user);
    }

    @Test
    @DisplayName("Test successful song update")
    public void testUpdateSongSuccess() throws MusicAppException {
        when(songRepository.findById(any(Integer.class))).thenReturn(Optional.of(song));
        when(songMapper.mapSongRequestToSong(any(SongRequest.class))).thenReturn(song);
        when(songRepository.save(any(Song.class))).thenReturn(song);

        GenericResponse response = songService.updateSong(songRequest, user);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("Song <testSong> was updated successfully for the user <test@test.com>.", response.getMessage());
    }

    @Test
    @DisplayName("Test song update by wrong user")
    public void testUpdateSongByWrongUser() {
        User wrongUser = new User();
        wrongUser.setId(2);
        wrongUser.setEmail("wrong@test.com");

        when(songRepository.findById(any(Integer.class))).thenReturn(Optional.of(song));
        MusicAppException exception = Assertions.assertThrows(MusicAppException.class, () -> songService.updateSong(songRequest, wrongUser));
        Assertions.assertEquals("The user wrong@test.com cannot update the song. Owner: test@test.com", exception.getMessage());
    }

    @Test
    @DisplayName("Test song not found for update")
    public void testSongNotFoundForUpdate() {
        when(songRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        MusicAppException exception = Assertions.assertThrows(MusicAppException.class, () -> songService.updateSong(songRequest, user));
        Assertions.assertEquals("The song cannot be updated. Song doesn't exist.", exception.getMessage());
    }

}
