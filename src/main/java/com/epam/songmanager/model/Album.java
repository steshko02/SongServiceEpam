package com.epam.songmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name="albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String name;

    private  int year;

    private  String notes;

//    @OneToMany(mappedBy="album", fetch=FetchType.EAGER)
//    private List<Song> songs;

    @ManyToMany
    @JoinTable(name="arist_album", joinColumns=@JoinColumn(name="album_id"),
            inverseJoinColumns=@JoinColumn(name="artist_id"))
    private Set<Artist> artists = new HashSet<>();


    @ManyToMany
    @JoinTable(name="genre_album", joinColumns=@JoinColumn(name="album_id"),
            inverseJoinColumns=@JoinColumn(name="genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Album(){
    }

    public Album(Long id, String name, int year, String notes) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.notes = notes;
    }

    public Album(int year, String album) {
        this.name = album;
        this.year = year;
    }
}
