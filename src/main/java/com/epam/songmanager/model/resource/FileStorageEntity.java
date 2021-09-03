package com.epam.songmanager.model.resource;

import java.io.InputStream;


public class FileStorageEntity extends ResourceDecorator {

    public FileStorageEntity(ResourceObj delegate) {
        super(delegate);
    }

    @Override
    public InputStream read() {
        return super.read();
    }

    @Override
    public void save(InputStream inputStream) {

    }
}
