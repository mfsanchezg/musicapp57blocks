package io._57blocks.music.controller;

import io._57blocks.music.dto.SongRequest;
import io._57blocks.music.dto.SongResponse;
import io._57blocks.music.entities.User;
import io._57blocks.music.exceptions.MusicAppException;
import io._57blocks.music.services.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private")
@RequiredArgsConstructor
public class MusicController {

    private final SongService songService;

    @GetMapping(value = "/songs", params = { "page", "size" })
    private List<SongResponse> getSongs(@RequestParam int page, @RequestParam int size, Authentication auth){
        User user = (User) auth.getPrincipal();
        return songService.getPrivateSongs(page, size, user);

    }

    @PostMapping(value = "/song")
    private ResponseEntity<Object> createSong(@Valid @RequestBody SongRequest songRequest, Authentication auth){
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(songService.createSong(songRequest, user));
    }

    @PatchMapping(value = "/song")
    private ResponseEntity<Object> updateSong(@Valid @RequestBody SongRequest songRequest, Authentication auth) throws MusicAppException {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(songService.updateSong(songRequest, user));
    }
}
