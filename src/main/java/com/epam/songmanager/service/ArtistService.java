package com.epam.songmanager.service;

import com.epam.songmanager.model.Artist;
import com.epam.songmanager.model.Genre;

import java.util.List;

public interface ArtistService {

    Long add(Artist artist);
    Long edit(Artist artist,Long id);
    Artist get(Long id);
    Long[] delete(Long ...id);
    List<Genre> getByFilters(String name, Long[] id);
}
