package com.epam.songmanager.service;


import com.epam.songmanager.model.file_entity.ResourceDecorator;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

public interface StorageService <T extends ResourceDecorator> {

    void init();

    String store(InputStream entity) throws IOException, NoSuchAlgorithmException;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    T create(String cs,String path,long size);

}