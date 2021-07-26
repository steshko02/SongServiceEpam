package com.epam.songmanager.controllers;

import com.epam.songmanager.model.Song;
import com.epam.songmanager.repository.ResourceRepository;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.SongService;
import com.epam.songmanager.utils.AudioParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class SongController {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SongService songService;

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private AudioParser audioParser;

    @RequestMapping(value = "songs/{songId}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity playAudio(@PathVariable("songId") Long songId) throws FileNotFoundException {
        Song song = songRepository.getById(songId);
        long length = song.getResource().getSize();
        InputStreamResource inputStreamResource = new InputStreamResource( new FileInputStream(song.getResource().getPath()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(length);
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity(inputStreamResource, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/songs/add")
    Long uploadFile(@RequestParam MultipartFile file) throws Exception {

        return  null;
    }
}
