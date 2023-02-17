package io._57blocks.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongResponse {

    private String name;
    private String artist;
    private String genre;
    private String year;
    private String country;
    private Boolean isPrivate;
    private String username;

}
