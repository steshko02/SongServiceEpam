package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.ServiceSwitcher;
import com.epam.songmanager.service.interfaces.SongService;
import com.epam.songmanager.service.impl.FileSystemStorageService;
import com.epam.songmanager.service.impl.MinioService;
import io.minio.errors.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class SongController {

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SongService songService;
    @Autowired
    private MinioService minioService;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @Autowired
    private ServiceSwitcher serviceSwitcher;

  //много логики
    @SneakyThrows
    @RequestMapping(value = "songs/{songId}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity playAudio(@PathVariable("songId") Long songId) throws EntityNotFoundException {
        Song song = songService.getById(songId);
        long length = song.getResource().getSize();
        InputStreamResource inputStreamResource = null;
        inputStreamResource = (InputStreamResource) serviceSwitcher.getByStorageType(song.getResource().getType()).
                getResource(song.getResource().getPath());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(length);
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity(inputStreamResource, httpHeaders, HttpStatus.OK);
    }

}
