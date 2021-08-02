package com.epam.songmanager.utils;

import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.dto.AlbumDto;

public interface MappingUtilsAlbums {
    AlbumDto mapToDto(Album entity);
    Album mapToEntity(AlbumDto dto);
}
