package io._57blocks.music.utils;

import io._57blocks.music.dto.SongRequest;
import io._57blocks.music.dto.SongResponse;
import io._57blocks.music.entities.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    Song mapSongRequestToSong(SongRequest songRequest);
    @Mapping(source = "user.email", target = "username")
    SongResponse mapSongToSongResponse(Song song);

    List<SongResponse> mapSongToSongsResponse(List<Song> songs);
}
