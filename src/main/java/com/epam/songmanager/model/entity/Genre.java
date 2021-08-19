package com.epam.songmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
