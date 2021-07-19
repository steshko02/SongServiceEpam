package com.epam.songmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String path;

    private  long size;

    private  String checksum;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private  Song song;

    public  Resource(){

    }
}
