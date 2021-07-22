package com.epam.songmanager.controllers;

import com.epam.songmanager.model.Album;
import com.epam.songmanager.model.Resource;
import com.epam.songmanager.model.Song;
import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Controller
public class testController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;



//    @GetMapping("/test")
//    public String  testRes() throws IOException, NoSuchAlgorithmException {
//        resourceService.addResource("C:\\Users\\stesh\\OneDrive\\Рабочий стол\\Король и Шут - Ели Мясо Мужики.mp3");
//
//        return "all is OK!";
//    }

//    @GetMapping("/test2")
//    public String  testSong() {
//
//        String name = "KISH";
//        String notes = "dsfsdfs";
//        Resource resource = resourceService.get(2L);
//       // Album album = new Album(1L,"name",1234,"notes",null);
//
//        //albumRepository.save(album);
//
//        songService.addSong(songService.create(name,1231,notes,resource,albumRepository.getById(6L)));
//
//        return "all is OK!";
//    }

    @RequestMapping(value = "test3/{songId}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity playAudio(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("songId") Long songId) throws FileNotFoundException {

        Song song = songRepository.getById(songId);

        String str = song.getName();
        long length = song.getResource().getSize();

        InputStreamResource inputStreamResource = new InputStreamResource( new FileInputStream(song.getResource().getPath()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(length);
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity(inputStreamResource, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/test4")
    public String  testSong1() {

        return "audio";
    }
}
