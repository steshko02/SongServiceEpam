package com.epam.songmanager.controllers;

import com.epam.songmanager.model.Genre;
import com.epam.songmanager.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> getGenres() {
           return genreService.getAll();
    }

    @DeleteMapping("/genres")
    public Long[] delete(@RequestParam Long[] ids){
        return  genreService.delete(ids);
    }

    @PostMapping("/genres/add")
    public  Long add(@RequestBody Genre genre){
        return   genreService.add(genre);
    }
}
