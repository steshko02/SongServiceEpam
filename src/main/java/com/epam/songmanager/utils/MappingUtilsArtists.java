package com.epam.songmanager.utils;

import com.epam.songmanager.model.Artist;
import com.epam.songmanager.model.dto.ArtistDto;

public interface MappingUtilsArtists {
    ArtistDto mapToDto(Artist entity);
    Artist mapToEntity(ArtistDto dto);
}
