package com.epam.songmanager.service.interfaces;

import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;

public interface SongService {

    void addSong(Song song);
    Song create(String name, int year, String notes, Resource resource, Album album);
    Song getById(Long id);
    org.springframework.core.io.Resource getSongAsResource(Long id);
}
