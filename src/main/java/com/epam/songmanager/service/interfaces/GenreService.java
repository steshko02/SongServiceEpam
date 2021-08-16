package com.epam.songmanager.service.interfaces;

import com.epam.songmanager.model.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre create(String name);

    Long add(Genre genre);

    Long[] delete(Long ...id);

    List<Genre> getAll();


    Genre getById(Long id);
}
