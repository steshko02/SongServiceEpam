package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.interfaces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ArtistRepository artistRepository;


    @Override
    public Long add(Album album) {
        albumRepository.save(album);
        return album.getId();
    }

    @Override
    public Long edit(Album album, Long id) {
        Album albumForEdit = albumRepository.getById(id);
        if (checkExistGenres(album.getGenres())
                && checkExistArtists(album.getArtists()) ){
            albumForEdit.setName(album.getName());
            albumForEdit.setNotes(album.getNotes());
            albumForEdit.setGenres(album.getGenres());
            albumForEdit.setArtists(album.getArtists());
            albumRepository.save(albumForEdit);
        }
        return albumForEdit.getId();
    }

    private boolean checkExistGenres(Set<Genre> genres){
        for (Genre g: genres) {
            if(!genreRepository.existsById(g.getId()))
                return false;
        }
        return true;
    }

    private boolean checkExistArtists(Set<Artist> artists){
        for (Artist a: artists) {
            if(!artistRepository.existsById(a.getId()))
                return false;
        }
        return true;
    }

//    public Long edit(Album album, Long id) {
//        Album albumForEdit = albumRepository.getById(id);
//        if (checkExistGenres(album.ge())){
//            artistForEdit.setName(artist.getName());
//            artistForEdit.setNotes(artist.getNotes());
//            artistForEdit.setGenres(artist.getGenres());
//            artistRepository.save(artistForEdit);
//        }
//        return artistForEdit.getId();
//    }

    @Override
    public Album get(Long id) {
        return albumRepository.getById(id);
    }

    @Override
    public Long[] delete(Long... ids) {
        for (Long id: ids) {
            albumRepository.deleteById(id);
        }
        return ids;
    }

    @Override
    public Album findByName(String name) {
        return albumRepository.findByName(name);
    }
}
