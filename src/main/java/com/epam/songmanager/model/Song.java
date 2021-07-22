package com.epam.songmanager.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name="songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String name;

    private  int year;

    private String notes;

    @ManyToOne (optional=false)
    private  Album album;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    private  Resource resource;

    public Song(){
    }

    public Song(String name, int year, String notes, Album album, Resource resource) {
        this.name = name;
        this.year = year;
        this.notes = notes;
        this.album = album;
        this.resource = resource;
    }
}
