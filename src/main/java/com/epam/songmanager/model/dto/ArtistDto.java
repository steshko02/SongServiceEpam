package com.epam.songmanager.model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ArtistDto {
    private Long id;

    private  String name;

    private  String notes;

    Set<Long> genres = new HashSet<>();


    public ArtistDto() {
    }
}
