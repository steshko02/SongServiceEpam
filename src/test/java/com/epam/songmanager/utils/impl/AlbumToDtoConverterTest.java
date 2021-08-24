package com.epam.songmanager.utils.impl;

import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.converters.AlbumToDtoConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AlbumToDtoConverterTest {

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private AlbumToDtoConverter mappingAlbumUtils;

     static  Set<Genre>  genres = new HashSet<>();
      static  Set<Long> genresId = new HashSet<>();
     static  Set<Artist> artists = new HashSet<>();
     static  Set<Long> artistsId = new HashSet<>();

     static Album album;
     static AlbumDto albumDto;

    @BeforeAll
    public static void setUp() {

        genres.add(new Genre(2L,"genre2"));
        genres.add(new Genre(1L,"genre1"));

        genresId.add(1L);
        genresId.add(2L);

        artists.add( new Artist(1L,"name1", "notes"));
        artists.add( new Artist(2L,"name2", "notes"));

        artistsId.add(1L);
        artistsId.add(2L);

        album = new Album(1L,"name",2000, "notes",artists,genres);
        albumDto = new AlbumDto(1L,"name",2000, "notes",genresId,artistsId);
    }
//
//    @Test
//    void mapToDto() {
//        assertEquals(mappingAlbumUtils.mapToDto(album),albumDto);
//    }
//
//    @Test
//    void mapToEntity() {
//        Mockito.when(artistRepository.getById(1L)).thenReturn(new Artist(1L,"name1", "notes"));
//        Mockito.when(artistRepository.getById(2L)).thenReturn(new Artist(2L,"name2", "notes"));
//        Mockito.when(genreRepository.getById(1L)).thenReturn(new Genre(1L,"genre1"));
//        Mockito.when(genreRepository.getById(2L)).thenReturn(new Genre(2L,"genre2"));
//        assertEquals(mappingAlbumUtils.mapToEntity(albumDto),album);
//    }
}