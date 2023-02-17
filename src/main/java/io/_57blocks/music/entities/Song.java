package io._57blocks.music.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
@SequenceGenerator(name="song_id_seq", initialValue=30, allocationSize=100)
public class Song {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="song_id_seq")
    private Integer id;
    private String name;
    private String artist;
    private String genre;
    private String year;
    private String country;
    private Boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

}
