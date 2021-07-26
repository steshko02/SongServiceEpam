package com.epam.songmanager.controllers;

import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.model.dto.ArtistDto;
import com.epam.songmanager.service.AlbumService;
import com.epam.songmanager.utils.MappingUtilsAlbums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private MappingUtilsAlbums mappingUtilsAlbums;


    @PostMapping("/albums")
    public  Long add(@RequestBody AlbumDto albumDto){
        return albumService.add(mappingUtilsAlbums.mapToEntity(albumDto));
    }

    @PutMapping("/albums/{id}")
    public  Long edit(@RequestBody AlbumDto albumDto,@PathVariable Long id){
        return albumService.edit( mappingUtilsAlbums.mapToEntity(albumDto), id);
    }
    @GetMapping("/albums/{id}")
    public AlbumDto getGenres(@PathVariable Long id) {
        return mappingUtilsAlbums.mapToDto(albumService.get(id));
    }

    @DeleteMapping("/albums")
    public Long[] delete(@RequestParam Long[] id){
        return  albumService.delete(id);
    }

}
