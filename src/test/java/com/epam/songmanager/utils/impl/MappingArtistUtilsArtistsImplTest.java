package com.epam.songmanager.utils.impl;

import com.epam.songmanager.model.dto.ArtistDto;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.dto_service.MappingArtistUtilsArtistsImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MappingArtistUtilsArtistsImplTest {

    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private MappingArtistUtilsArtistsImpl mappingArtistUtilsArtists;

    static Artist artist;
    static ArtistDto artistDto;

    static Set<Genre> genres = new HashSet<>();
    static  Set<Long> genresId = new HashSet<>();


    @BeforeAll
    public static void setUp() {

        genres.add(new Genre(2L,"genre2"));
        genres.add(new Genre(1L,"genre1"));

        genresId.add(1L);
        genresId.add(2L);

        artist =  new Artist(1L,"name1", "notes",genres);
        artistDto = new ArtistDto(1L,"name1", "notes",genresId);
    }

    @Test
    void mapToDto() {
        assertEquals(mappingArtistUtilsArtists.mapToDto(artist),artistDto);
    }

    @Test
    void mapToEntity() {

        Mockito.when(genreRepository.getById(2L)).thenReturn(new Genre(2L,"genre2"));
        Mockito.when(genreRepository.getById(1L)).thenReturn(new Genre(1L,"genre1"));
        assertEquals(mappingArtistUtilsArtists.mapToEntity(artistDto),artist);
    }
}