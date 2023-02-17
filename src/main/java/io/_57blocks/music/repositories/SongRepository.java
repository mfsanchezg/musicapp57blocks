package io._57blocks.music.repositories;

import io._57blocks.music.entities.Song;
import io._57blocks.music.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {

    Page<Song> findByIsPrivate(boolean isPrivate, Pageable pageable);

    Page<Song> findByUser(User user, Pageable pageable);

}
