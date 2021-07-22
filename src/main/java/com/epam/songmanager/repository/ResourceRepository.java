package com.epam.songmanager.repository;

import com.epam.songmanager.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,Long> {
}
