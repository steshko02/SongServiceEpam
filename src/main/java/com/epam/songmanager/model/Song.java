package com.epam.songmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
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

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    private  Album album;

    @OneToOne(optional = false, mappedBy = "song")
    @JoinColumn(name = "resource_id")
    private  Resource resource;

    public Song(){

    }

}
