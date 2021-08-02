package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.entity.Genre;
import com.epam.songmanager.repository.ArtistRepository;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Long add(Artist artist) {
        artistRepository.save(artist);
        return artist.getId();
    }

    private boolean checkExist(Set<Genre> genres){
        for (Genre g: genres) {
            if(!genreRepository.existsById(g.getId()))
                return false;
        }
        return true;
    }

    @Override
    public Long edit(Artist artist,Long id) {
        Artist artistForEdit = artistRepository.getById(id);
        if (checkExist(artist.getGenres())){
            artistForEdit.setName(artist.getName());
            artistForEdit.setNotes(artist.getNotes());
            artistForEdit.setGenres(artist.getGenres());
            artistRepository.save(artistForEdit);
        }
        return artistForEdit.getId();
    }

    @Override
    public Artist get(Long id) {
       return artistRepository.getById(id);
    }

    @Override
    public Long[] delete(Long... ids) {
        for (Long id: ids) {
            artistRepository.deleteById(id);
        }
        return ids;
    }

    public Set<Artist> getByFiltersName(String name) {
        return artistRepository.findByNameStartingWith(name);
    }

    public Set<Artist> getByFiltersGenres(Set<Artist> artistsForSort,Set<Genre> genres) {

        Set<Artist> artists = new HashSet<>();

        for (Artist a:artistsForSort){
            for (Genre g: genres) {
                if(a.getGenres().contains(g)) {
                    artists.add(a);
                    break;
                }
            }
        }

        return  artists;
    }

    public Set<Artist> getByFullFilter(String name,Set<Genre> genres){

        return getByFiltersGenres(getByFiltersName(name),genres);
    }
}
