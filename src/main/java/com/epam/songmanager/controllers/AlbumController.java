package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.service.interfaces.AlbumService;
import com.epam.songmanager.service.interfaces.MappingUtilsAlbums;
import org.springframework.beans.factory.annotation.Autowired;
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
    public  Long edit(@RequestBody AlbumDto albumDto,@PathVariable Long id) throws EntityNotFoundException {
        return albumService.edit( mappingUtilsAlbums.mapToEntity(albumDto), id);
    }
    @GetMapping("/albums/{id}")
    public AlbumDto getGenres(@PathVariable Long id) throws EntityNotFoundException {
        return mappingUtilsAlbums.mapToDto(albumService.get(id));
    }

    @DeleteMapping("/albums")
    public Long[] delete(@RequestParam Long[] id) throws EntityNotFoundException{
        return  albumService.delete(id);
    }

}
