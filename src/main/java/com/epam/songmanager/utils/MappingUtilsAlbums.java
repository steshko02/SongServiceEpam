package com.epam.songmanager.utils;

import com.epam.songmanager.model.Album;
import com.epam.songmanager.model.Artist;
import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.model.dto.ArtistDto;

public interface MappingUtilsAlbums {
    AlbumDto mapToDto(Album entity);
    Album mapToEntity(AlbumDto dto);
}
