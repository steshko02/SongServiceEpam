package com.epam.songmanager.controllers;

import com.epam.songmanager.model.Album;
import com.epam.songmanager.model.Artist;
import com.epam.songmanager.model.Genre;
import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.service.AlbumService;
import com.epam.songmanager.utils.MappingUtilsAlbums;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AlbumService albumService;
    @MockBean
    private MappingUtilsAlbums mappingUtilsAlbums;
    @Test
    public void givenAlbum_whenAdd_thenStatus201andReturnID() throws Exception {

        Album album = new Album(1L,"TEST",1999,"Some text");

        Mockito.when(albumService.add(Mockito.any())).thenReturn(album.getId());

        mockMvc.perform(
                post("/albums")
                        .content(objectMapper.writeValueAsString(album))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().json(objectMapper.writeValueAsString(album.getId())));
    }
    @Test
    public void deleteAlbum_whenAdd_thenStatus201andReturnID() throws Exception {
        Long[] id = {22L,23L};

        Mockito.when(albumService.delete(Mockito.any())).thenReturn(id);

        mockMvc.perform(
                delete("/albums")
                        .param("id", String.valueOf(22L))
                        .param("id", String.valueOf(23L))
        )
                .andExpect(content().json(objectMapper.writeValueAsString(id)));
    }

    @Test
    public void GetAlbumsThenStatus201andReturnID() throws Exception {
        Album album1 = new Album(1L,"TEST",1999,"Some text");
        AlbumDto albumDto = new AlbumDto(1L,"TEST",1999,"Some text");

        Mockito.when(albumService.get(1L)).thenReturn(album1);
        Mockito.when(mappingUtilsAlbums.mapToDto(album1)).thenReturn(albumDto);
        mockMvc.perform(
                get("/albums/1")
        )
                .andExpect(content().json(objectMapper.writeValueAsString(albumDto)));

    }
    @Test
    public void PeuAlbumsThenStatus201andReturnID() throws Exception {
        AlbumDto albumDto = new AlbumDto(1L,"TEST",1999,"Some text");
        Album album1 = new Album(1L,"TEST",1999,"Some text");

        Album album2 = new Album(2L,"TEST",1999,"Some text");

        Mockito.when(albumService.edit(album1,2L)).thenReturn(album2.getId());
        Mockito.when(mappingUtilsAlbums.mapToEntity(albumDto)).thenReturn(album1);
        mockMvc.perform(
                put("/albums/2")
                        .content(objectMapper.writeValueAsString(albumDto))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().json(objectMapper.writeValueAsString(album2.getId())));

    }
}