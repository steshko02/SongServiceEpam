package com.epam.songmanager.service.converters;

import com.epam.songmanager.model.dto.ArtistDto;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToArtistConverter
        implements Converter<ArtistDto, Artist> {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Artist convert(ArtistDto dto) {
            Artist entity = new Artist();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setNotes(dto.getNotes());
            for (Long id: dto.getGenres()) {
                entity.getGenres().add(genreRepository.getById(id));
            }
            return entity;
    }
}
