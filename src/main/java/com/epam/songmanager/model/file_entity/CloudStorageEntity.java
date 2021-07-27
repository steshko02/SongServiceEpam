package com.epam.songmanager.model.file_entity;

import org.springframework.stereotype.Service;

import java.io.InputStream;


public class CloudStorageEntity extends BaseFile{
    public CloudStorageEntity(InputStream inputStream, String path, long size) {
        super(inputStream, path, size);
    }
}
