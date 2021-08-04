package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.service.ArtistService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArtistServiceImplTest {

    @MockBean
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistService artistService;

    @Test
    void add() {
        Artist artist = new Artist(1L,"name", "notes");
        Long id = artistService.add(artist);
        assertEquals(id,1L);
        Mockito.verify(artistRepository,Mockito.times(1)).save(artist);
    }

    @Test
    void edit() {
        Artist artist = new Artist(1L,"name", "notes");
        Artist newArtist = new Artist(1L,"name1", "notes1");
        Mockito.when(artistRepository.getById(artist.getId())).thenReturn(artist);
        assertEquals(artistService.edit(newArtist,1L),newArtist.getId());
    }

    @Test
    void get() {
        Artist artist = new Artist(1L,"name", "notes");
        Mockito.when(artistRepository.getById(1L)).thenReturn(artist);
        Artist fromMockRepos = artistService.get(1L);
        Mockito.verify(artistRepository,Mockito.times(1)).getById(1L);
        assertEquals(artist,fromMockRepos);
    }

    @Test
    void delete() {
        Long[] forDelete = {1L,2L};

        Long[] deleteId = artistService.delete(forDelete);

        Mockito.verify(artistRepository,Mockito.times(1)).deleteById(1L);
        Mockito.verify(artistRepository,Mockito.times(1)).deleteById(2L);

        assertEquals(forDelete,deleteId);
    }

}