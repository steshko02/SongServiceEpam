package com.epam.songmanager.service;

import com.epam.songmanager.model.Album;
import com.epam.songmanager.model.Artist;

public interface AlbumService {

    Long add(Album artist);
    Long edit(Album artist,Long id);
    Album get(Long id);
    Long[] delete(Long ...id);
    Album findByName(String name);
}
