package com.epam.songmanager.controllers;

import com.epam.songmanager.exceptions.EntityNotFoundException;
import com.epam.songmanager.model.entity.Song;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.service.interfaces.ResourceObjectService;
import com.epam.songmanager.service.interfaces.SongService;
import io.minio.errors.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class SongController {

    @Autowired
    private SongService songService;
    @Autowired
    private ResourceObjectService resourceObjService;

    @SneakyThrows
    @RequestMapping(value = "songs/{songId}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<Resource> playAudio(@PathVariable("songId") Long songId) throws EntityNotFoundException {
        Resource resource = songService.getSongAsResource(songId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(resource.contentLength());
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("songs/download/{songId}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Long songId) throws IOException {

        Song song = songService.getById(songId);
        Resource file = new ByteArrayResource(resourceObjService.getResource(song.getResourceObjId()).read().readAllBytes());
        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + song.getName()+".mp3" + "\"")
                .body(file);
    }

}
