package com.epam.songmanager.service.converters;

import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAlbumConvert
        implements Converter<AlbumDto,Album> {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Album convert(AlbumDto dto) {
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
