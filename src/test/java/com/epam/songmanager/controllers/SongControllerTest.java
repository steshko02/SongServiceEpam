package com.epam.songmanager.controllers;

import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.interfaces.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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

//        Long songId = 1L;
//        Resource resource = new ByteArrayResource("test".getBytes(StandardCharsets.UTF_8));
//        Mockito.when(songService.getSongAsResource(songId)).thenReturn(resource);
//
//        mockMvc.perform(get("/songs/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().bytes("test".getBytes(StandardCharsets.UTF_8)));
    }


}