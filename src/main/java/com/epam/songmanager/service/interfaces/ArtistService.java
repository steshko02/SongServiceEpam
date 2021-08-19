package com.epam.songmanager.service.interfaces;

import com.epam.songmanager.model.entity.Artist;

import java.util.List;
import java.util.Set;

public interface ArtistService {

    Long add(Artist artist);
    Long edit(Artist artist,Long id);
    Artist get(Long id);
    Long[] delete(Long ...id);
    List<Artist> getByFilters(String name, Long[] ids);
}
