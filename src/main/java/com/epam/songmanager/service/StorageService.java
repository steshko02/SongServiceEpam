package com.epam.songmanager.service;


import com.epam.songmanager.model.file_entity.BaseFile;
import org.springframework.core.io.Resource;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService <T extends BaseFile> {

    void init();

    T store(InputStream entity);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}