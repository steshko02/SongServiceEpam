package com.epam.songmanager.model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AlbumDto {
    private Long id;

    private  String name;

    private  int year;

    private  String notes;

    private Set<Long> genres = new HashSet<>();

    private  Set<Long> artists = new HashSet<>();

    public AlbumDto() {
    }
}
