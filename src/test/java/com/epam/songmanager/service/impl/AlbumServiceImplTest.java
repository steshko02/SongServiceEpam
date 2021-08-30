package com.epam.songmanager.service.impl;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.service.interfaces.AlbumService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

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
        Mockito.when(albumRepository.findById(album.getId())).thenReturn(java.util.Optional.of(album));
        assertEquals(albumService.edit(newAlbum,1L),newAlbum.getId());
    }

    @Test
    void get() {
        Album album = new Album(1L,"name",2000, "notes");
        Mockito.when(albumRepository.findById(album.getId())).thenReturn(java.util.Optional.of(album));
        Album fromMockRepos = albumService.get(1L);
        assertEquals(album,fromMockRepos);
        Mockito.verify(albumRepository,Mockito.times(1)).findById(1L);
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

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Test
    void findByNameIfNotExists() {
        Mockito.when(albumRepository.findByName("name1")).thenReturn(null);

        exception.expect(IndexOutOfBoundsException.class);

        Album album = albumRepository.findByName("name1");

        Mockito.verify(albumRepository,Mockito.times(1)).findByName("name1");



    }
}