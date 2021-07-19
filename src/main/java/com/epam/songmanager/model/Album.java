package com.epam.songmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
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

    @OneToMany(mappedBy="album", fetch=FetchType.EAGER)
    private List<Song> songs;

    public Album(){

    }
}
