package com.epam.songmanager.controllers;

import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.service.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenreService genreService;
    @Test
    public void givenAlbum_whenAdd_thenStatus201andReturnID() throws Exception {
        Genre genre = new Genre(1L,"POP");
        Mockito.when(genreService.add(Mockito.any())).thenReturn(genre.getId());
        mockMvc.perform(
                post("/genres/add")
                        .content(objectMapper.writeValueAsString(genre))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().json(objectMapper.writeValueAsString(genre.getId())));
    }
    @Test
    public void deleteGenreThenStatus201andReturnID() throws Exception {

        Long[] id = {22L,23L};
        Mockito.when(genreService.delete(Mockito.any())).thenReturn(id);
        mockMvc.perform(
                delete("/genres")
                        .param("ids", String.valueOf(22L))
                        .param("ids", String.valueOf(23L))
        )
                .andExpect(content().json(objectMapper.writeValueAsString(id)));
    }
    @Test
    public void GetGenreThenStatus201andReturnID() throws Exception {
        Genre genre1 = new Genre(1L,"POP");
        Genre genre2 = new Genre(2L,"FDf");
        Genre genre3 = new Genre(3L,"EQQE");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        genres.add(genre3);

        Mockito.when(genreService.getAll()).thenReturn(genres);
        mockMvc.perform(
                get("/genres")
                        .param("ids", String.valueOf(22L))
                        .param("ids", String.valueOf(23L))
        )
                .andExpect(content().json(objectMapper.writeValueAsString(genres)));

    }
}