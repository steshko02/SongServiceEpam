package com.epam.songmanager.service.interfaces;

import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.dto.ArtistDto;

public interface MappingUtilsArtists {
    ArtistDto mapToDto(Artist entity);
    Artist mapToEntity(ArtistDto dto);
}
