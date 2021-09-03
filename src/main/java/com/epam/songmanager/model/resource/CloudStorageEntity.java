package com.epam.songmanager.model.resource;


import java.io.InputStream;


public class CloudStorageEntity extends ResourceDecorator{


    public CloudStorageEntity(ResourceObj delegate) {
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

