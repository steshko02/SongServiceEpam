package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.parsers.AudioParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
class SongControllerTest {


    @MockBean
    private SongService songService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private GenreRepository genreRepository;
    @Test
    public void  playAudioTest() throws Exception {

        Long songId = 1L;
        Resource resource = new ByteArrayResource("test".getBytes(StandardCharsets.UTF_8));
        Mockito.when(songService.getSongAsResource(songId)).thenReturn(resource);

        mockMvc.perform(get("/songs/1"))
                .andExpect(status().isOk())
                .andExpect(content().bytes("test".getBytes(StandardCharsets.UTF_8)));

    }


}