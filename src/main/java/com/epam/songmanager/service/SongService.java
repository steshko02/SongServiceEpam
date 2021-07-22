package com.epam.songmanager.service;

import com.epam.songmanager.model.Album;
import com.epam.songmanager.model.Resource;
import com.epam.songmanager.model.Song;

public interface SongService {

    void addSong(Song song);
    Song create(String name, int year, String notes, Resource resource, Album album);
}
