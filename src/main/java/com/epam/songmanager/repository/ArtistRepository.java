package com.epam.songmanager.repository;

import com.epam.songmanager.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistRepository extends JpaRepository<Artist,Long> {

}
