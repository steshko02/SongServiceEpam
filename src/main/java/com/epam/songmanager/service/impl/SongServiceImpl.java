package com.epam.songmanager.service.impl;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Artist;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.interfaces.SongService;
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
    public Song getById(Long id) {
        Song song = songRepository.findById(id).orElse(null);
        if(song == null){
            throw new EntityNotFoundException(Song.class, "id", id.toString());
        }
        return song;
    }

    @Override
    public void addSong(Song song) {
        songRepository.save(song);
    }
}
