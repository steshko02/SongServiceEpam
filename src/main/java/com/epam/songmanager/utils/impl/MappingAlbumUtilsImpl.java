package com.epam.songmanager.utils.impl;

import com.epam.songmanager.model.Album;
import com.epam.songmanager.model.Artist;
import com.epam.songmanager.model.Genre;
import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.utils.MappingUtilsAlbums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingAlbumUtilsImpl implements MappingUtilsAlbums {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public AlbumDto mapToDto(Album entity) {
        AlbumDto dto = new AlbumDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNotes(entity.getNotes());
        dto.setYear(entity.getYear());
        for (Genre g: entity.getGenres()) {
            dto.getGenres().add(g.getId());
        }
        for (Artist a: entity.getArtists()) {
            dto.getArtists().add(a.getId());
        }
        return dto;
    }

    @Override
    public Album mapToEntity(AlbumDto dto) {
        Album entity = new Album();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setNotes(dto.getNotes());
        entity.setYear(dto.getYear());
        for (Long id: dto.getGenres()) {
            entity.getGenres().add(genreRepository.getById(id));
        }
        for (Long a: dto.getArtists()) {
            entity.getArtists().add(artistRepository.getById(a));
        }
        return entity;
    }
}
