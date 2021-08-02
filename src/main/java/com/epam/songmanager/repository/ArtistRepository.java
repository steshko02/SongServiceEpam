package com.epam.songmanager.repository;

import com.epam.songmanager.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ArtistRepository extends JpaRepository<Artist,Long> {

    Set<Artist> findByNameStartingWith(String name);

}
