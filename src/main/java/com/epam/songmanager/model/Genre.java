package com.epam.songmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String name;

    public Genre() {
    }
    public Genre(String name){
        this.name=name;
    }

    @ManyToMany(mappedBy = "genres")
    private Set<Album> albums = new HashSet<>();



}
