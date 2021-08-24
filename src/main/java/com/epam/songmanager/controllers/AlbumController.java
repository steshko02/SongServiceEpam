package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.dto.AlbumDto;
import com.epam.songmanager.model.entity.Album;
import com.epam.songmanager.service.interfaces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private ConversionService conversionService;

    @PostMapping("/albums")
    public  Long add(@RequestBody AlbumDto albumDto){
        return albumService.add(conversionService.convert(albumDto,Album.class));
    }

    @PutMapping("/albums/{id}")
    public  Long edit(@RequestBody AlbumDto albumDto,@PathVariable Long id) throws EntityNotFoundException {
        return albumService.edit( conversionService.convert(albumDto,Album.class), id);
    }
    @GetMapping("/albums/{id}")
    public AlbumDto getAlbum(@PathVariable Long id) throws EntityNotFoundException {
        return conversionService.convert(albumService.get(id),AlbumDto.class);
    }

    @DeleteMapping("/albums")
    public Long[] delete(@RequestParam Long[] id) throws EntityNotFoundException{
        return  albumService.delete(id);
    }

}
