package com.epam.songmanager.controllers;

import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.dto.ArtistDto;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.converters.ArtistToDtoConverter;
import com.epam.songmanager.service.interfaces.ArtistService;
import com.epam.songmanager.service.interfaces.GenreService;
import com.epam.songmanager.service.converters.DtoToArtistConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArtistService artistService;
    @MockBean
    private DtoToArtistConverter dtoToArtistConverter;
    @MockBean
    private ArtistToDtoConverter artistToDtoConverter;
    @MockBean
    private ArtistRepository artistRepository;
    @MockBean
    private GenreRepository genreRepository;


    @Test
    public void addArtistThenStatus201andReturnID() throws Exception {

        Artist artist = new Artist(1L,"name","notes");

        Mockito.when(artistService.add(Mockito.any())).thenReturn(artist.getId());

        mockMvc.perform(
                post("/artists")
                        .content(objectMapper.writeValueAsString(artist))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().json(objectMapper.writeValueAsString(artist.getId())));
    }
    @Test
    public void deleteArtistThenStatus201andReturnID() throws Exception {

        Long[] id = {22L,23L};
        Mockito.when(artistService.delete(Mockito.any())).thenReturn(id);
        mockMvc.perform(
                delete("/artists")
                        .param("ids", String.valueOf(22L))
                        .param("ids", String.valueOf(23L))
        )
                .andExpect(content().json(objectMapper.writeValueAsString(id)));
    }

    @Test
    public void GetArtistThenStatus201andReturnID() throws Exception {
        Artist artist1 = new Artist(1L,"name1","notes1");
        ArtistDto artistDto = new ArtistDto(1L,"name1","notes1");

        Mockito.when(artistService.get(1L)).thenReturn(artist1);
        Mockito.when(artistToDtoConverter.convert(artist1)).thenReturn(artistDto);
        mockMvc.perform(
                get("/artists/1")
        )
                .andExpect(content().json(objectMapper.writeValueAsString(artistToDtoConverter.convert(artist1))));

    }
    @Test
    public void PutArtistThenStatus201andReturnID() throws Exception {
        Artist artist1 = new Artist(1L,"name1","notes1");
        ArtistDto artistDto = new ArtistDto(1L,"name1","notes1");

        Artist artist2 = new Artist(2L,"name2","notes2");

        Mockito.when(artistService.edit(artist1,2L)).thenReturn(artist2.getId());
        Mockito.when(dtoToArtistConverter.convert(artistDto)).thenReturn(artist1);
        mockMvc.perform(
                put("/artists/2")
                        .content(objectMapper.writeValueAsString(artistDto))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().json(objectMapper.writeValueAsString(artist2.getId())));

    }


}