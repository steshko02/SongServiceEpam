package com.epam.songmanager.repository;

import com.epam.songmanager.model.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,Long> {
    boolean existsByPath(String path);
    boolean existsByChecksum(String sum);
}
