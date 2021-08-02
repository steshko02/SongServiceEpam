package com.epam.songmanager.repository;

import com.epam.songmanager.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository  extends JpaRepository<Album,Long> {
    Album findByName(String name);
}
