package com.epam.songmanager.repository;

import com.epam.songmanager.model.Artist;
import com.epam.songmanager.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ArtistRepository extends JpaRepository<Artist,Long> {

    Set<Artist> findByNameStartingWith(String name);

}
