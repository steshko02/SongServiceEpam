package com.epam.songmanager.model.file_entity;

import java.io.IOException;
import java.io.InputStream;


public class CloudStorageEntity extends ResourceDecorator {
    private String path;

    private long size;

    @Override
    public ResourceObj save(ResourceObj resourceObj) throws IOException {
        return null;
    }
}
