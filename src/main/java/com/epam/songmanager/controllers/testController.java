package com.epam.songmanager.controllers;

import com.epam.songmanager.repository.AlbumRepository;
import com.epam.songmanager.repository.SongRepository;
import com.epam.songmanager.service.interfaces.ResourceService;
import com.epam.songmanager.service.interfaces.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@Controller
public class testController {

    @GetMapping("/test4")
    public String  testSong1() {
        return "audio";
    }
}
