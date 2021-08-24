package com.epam.songmanager.service.converters;

import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.model.dto.ArtistDto;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.interfaces.MappingUtilsArtists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ArtistToDtoConverter
        implements Converter< Artist, ArtistDto> {


    @Override
    public ArtistDto convert(Artist entity) {
        ArtistDto dto = new ArtistDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNotes(entity.getNotes());
        for (Genre g: entity.getGenres()) {
            dto.getGenres().add(g.getId());
        }
        return dto;
    }
}
