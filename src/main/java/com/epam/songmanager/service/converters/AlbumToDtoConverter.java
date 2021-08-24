package com.epam.songmanager.service.converters;

import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.interfaces.MappingUtilsAlbums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class AlbumToDtoConverter
        implements Converter<Album, AlbumDto> {


    @Override
    public AlbumDto convert(Album entity) {
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
}
