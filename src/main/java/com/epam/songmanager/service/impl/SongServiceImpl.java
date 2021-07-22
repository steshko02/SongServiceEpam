package com.epam.songmanager.service.impl;

import com.epam.songmanager.model.Album;
import com.epam.songmanager.model.Resource;
import com.epam.songmanager.model.Song;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    public Song create(String name, int year, String notes, Resource resource, Album album){
        return new Song(name,year,notes,album,resource);
    }

    @Override
    public void addSong(Song song) {
        songRepository.save(song);
    }
}
