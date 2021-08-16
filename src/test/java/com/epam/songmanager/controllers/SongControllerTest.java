package com.epam.songmanager.controllers;

import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.parsers.AudioParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(SongController.class)
class SongControllerTest {

    @MockBean
    private SongRepository songRepository;
    @MockBean
    private SongService songService;
    @MockBean
    private AudioParser audioParser;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void playAudio() throws Exception {
//        Resource resource = new Resource(1L,"path",99,"checkSum");
//        Album album = new Album(1L,"TEST",1999,"Some text");
//        Song song = new Song(1L,"name",1997,"notes",album,resource);
//
//        Mockito.when(songRepository.getById(1L)).thenReturn(song);
//
//        InputStreamResource inputStream = new InputStreamResource(new ByteArrayInputStream("song.mp3".getBytes()));
//
//
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        httpHeaders.setContentLength("song.mp3".getBytes().length);
//        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
//
//        ResponseEntity responseEntity = new ResponseEntity(inputStream, httpHeaders, HttpStatus.OK);
//
//
//        mockMvc.perform(
//                post("/songs/1")
//        )
//                .andExpect((ResultMatcher) responseEntity);
    }
}