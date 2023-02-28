package io._57blocks.music.services;

import io._57blocks.music.dto.GenericResponse;
import io._57blocks.music.dto.SongRequest;
import io._57blocks.music.dto.SongResponse;
import io._57blocks.music.entities.Song;
import io._57blocks.music.entities.User;
import io._57blocks.music.exceptions.MusicAppException;
import io._57blocks.music.repositories.SongRepository;
import io._57blocks.music.utils.SongMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {

    private final SongRepository songRepository;

    private final SongMapper songMapper;


    public List<SongResponse> getPublicSongs(Integer page, Integer size){

        List<Song> listSongs = songRepository.findByIsPrivate(false, PageRequest.of(page, size)).getContent();

        return songMapper.mapSongToSongsResponse(listSongs);
    }

    public List<SongResponse> getPrivateSongs(Integer page, Integer size, User user){

        List<Song> listSongs = songRepository.findByUser(user, PageRequest.of(page, size)).getContent();

        return songMapper.mapSongToSongsResponse(listSongs);
    }

    public GenericResponse createSong(SongRequest songRequest, User user){

        Song songToSave = songMapper.mapSongRequestToSong(songRequest);
        songToSave.setUser(user);

        songRepository.save(songToSave);

        log.info("Song <" + songToSave.getName() + "> was created successfully for the user <" + user.getEmail() + ">.");

        GenericResponse genericResponse = GenericResponse
                .builder()
                .status(HttpStatus.OK.value())
                .message("Song <" + songToSave.getName() + "> was created successfully for the user <" + user.getEmail() + ">.")
                .build();

        return genericResponse;
    }

    public GenericResponse updateSong(SongRequest songRequest, User user) throws MusicAppException {

        Optional<Song> song = songRepository.findById(songRequest.getId());

        if(song.isPresent()){
            if(song.get().getUser().getId().intValue() == user.getId().intValue()){
                Song songToUpdate = songMapper.mapSongRequestToSong(songRequest);
                songToUpdate.setId(song.get().getId());
                songToUpdate.setUser(user);
                songRepository.save(songToUpdate);
            }else{
                throw new MusicAppException("The user " + user.getEmail() + " cannot update the song. Owner: " + song.get().getUser().getEmail());
            }
        }else{
            throw new MusicAppException("The song cannot be updated. Song doesn't exist.");
        }

        log.info("Song <" + songRequest.getName() + "> was updated successfully for the user <" + user.getEmail() + ">.");

        GenericResponse genericResponse = GenericResponse
                .builder()
                .status(HttpStatus.OK.value())
                .message("Song <" + songRequest.getName() + "> was updated successfully for the user <" + user.getEmail() + ">.")
                .build();

        return genericResponse;
    }
}
