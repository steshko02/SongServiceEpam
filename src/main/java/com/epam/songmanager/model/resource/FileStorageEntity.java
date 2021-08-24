package com.epam.songmanager.model.resource;

import java.io.IOException;
import java.io.InputStream;


public class FileStorageEntity extends ResourceDecorator {


    public FileStorageEntity(String path, long size, String checksum) {
        super(path, size, checksum);
    }

    @Override
    public InputStream read() throws IOException {
        try {
            return super.read();
        } catch (IOException e) {
            throw new IOException("Exception occurred while decompressing input stream. ", e);
        }
    }

}
