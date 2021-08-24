package com.epam.songmanager.service.impl;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.model.entity.Resource;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.interfaces.StorageSwitcher;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private StorageSwitcher serviceSwitcher;
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

    @SneakyThrows
    @Override
    public org.springframework.core.io.Resource getSongAsResource(Long id){
        Song song = getById(id);
       return  serviceSwitcher.getByType(song.getResource().getType()).
                getResource(song.getResource().getPath());
    }

    @Override
    public void addSong(Song song) {
        songRepository.save(song);
    }
}
