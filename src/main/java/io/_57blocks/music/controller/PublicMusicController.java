package io._57blocks.music.controller;

import io._57blocks.music.dto.SongResponse;
import io._57blocks.music.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicMusicController {

    private final SongService songService;

    @GetMapping(value = "/health")
    public String health(){
        return "Hello from music-app-57blocks";
    }

    @GetMapping(value = "/songs", params = { "page", "size" })
    private List<SongResponse> getSongs(@RequestParam int page, @RequestParam int size){

        return songService.getPublicSongs(page, size);

    }
}
