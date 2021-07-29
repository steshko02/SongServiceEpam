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

    public ArtistDto(Long id, String name, String notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }
}
