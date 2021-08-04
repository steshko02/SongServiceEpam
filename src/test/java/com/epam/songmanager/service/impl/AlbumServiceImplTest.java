package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.AlbumService;
import com.epam.songmanager.service.ResourceService;
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
class AlbumServiceImplTest {

    @MockBean
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @Test
    void add() {
        Album album = new Album(1L,"name",2000, "notes");
        Long id = albumService.add(album);
        assertEquals(id,1L);
        Mockito.verify(albumRepository,Mockito.times(1)).save(album);
    }

    @Test
    void edit() {
        Album album = new Album(1L,"name",2000, "notes");
        Album newAlbum = new Album(1L,"name1",2002, "notes1");
        Mockito.when(albumRepository.getById(album.getId())).thenReturn(album);
        assertEquals(albumService.edit(newAlbum,1L),newAlbum.getId());
    }

    @Test
    void get() {
        Album album = new Album(1L,"name",2000, "notes");
        Mockito.when(albumRepository.getById(1L)).thenReturn(album);
        Album fromMockRepos = albumService.get(1L);
        Mockito.verify(albumRepository,Mockito.times(1)).getById(1L);
        assertEquals(album,fromMockRepos);
    }

    @Test
    void delete() {
        Long[] forDelete = {1L,2L};

        Long[] deleteId = albumService.delete(forDelete);

        Mockito.verify(albumRepository,Mockito.times(1)).deleteById(1L);
        Mockito.verify(albumRepository,Mockito.times(1)).deleteById(2L);

        assertEquals(forDelete,deleteId);
    }

    @Test
    void findByNameIfExists() {
        Album album = new Album(1L,"name",2000, "notes");
        Mockito.when(albumRepository.findByName("name")).thenReturn(album);
        Album album1 = albumService.findByName("name");
        Mockito.verify(albumRepository,Mockito.times(1)).findByName("name");
        assertEquals(album,album1);
    }
    @Test
    void findByNameIfNotExists() {
        Mockito.when(albumRepository.findByName("name1")).thenReturn(null);
        Album album1 = albumService.findByName("name1");
        Mockito.verify(albumRepository,Mockito.times(1)).findByName("name1");
        assertNull(album1);
    }
}