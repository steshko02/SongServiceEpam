package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.interfaces.GenreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GenreServiceImplTest {

    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private GenreService genreService;
    @Test
    void create() {
        Genre genre = genreService.create("genre");
        assertEquals(genre, new Genre("genre"));
    }

    @Test
    void add() {
        Genre genre = new Genre(1L,"name");
        Long id = genreService.add(genre);
        assertEquals(id,1L);
        Mockito.verify(genreRepository,Mockito.times(1)).save(genre);
    }

    @Test
    void delete() {
        Long[] forDelete = {1L,2L};

        Long[] deleteId = genreService.delete(forDelete);

        Mockito.verify(genreRepository,Mockito.times(1)).deleteById(1L);
        Mockito.verify(genreRepository,Mockito.times(1)).deleteById(2L);

        assertEquals(forDelete,deleteId);
    }

    @Test
    void getAll() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L,"genre1"));
        genres.add(new Genre(2L,"genre2"));
        Mockito.when(genreRepository.findAll()).thenReturn(genres);
        assertEquals(genres,genreService.getAll());
        Mockito.verify(genreRepository,Mockito.times(1)).findAll();
    }
}