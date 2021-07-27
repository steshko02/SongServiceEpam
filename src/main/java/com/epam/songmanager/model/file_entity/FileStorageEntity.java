package com.epam.songmanager.model.file_entity;

import java.io.InputStream;

public class FileStorageEntity extends BaseFile{
    public FileStorageEntity(InputStream inputStream, String path, long size) {
        super(inputStream, path, size);
    }
}
