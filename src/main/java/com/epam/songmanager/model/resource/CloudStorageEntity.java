package com.epam.songmanager.model.resource;


import com.epam.songmanager.model.entity.StorageType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.io.InputStream;

@Document
public class CloudStorageEntity extends ResourceDecorator {


    public CloudStorageEntity() {

    }

    public CloudStorageEntity(StorageType cloudSystem) {
        super(cloudSystem);
    }

    @Override
    public InputStream read() throws IOException {
        try {
            return super.read();
        } catch (IOException e) {
            throw new IOException("Exception occurred while decompressing input stream. ", e);
        }
    }

    @Override
    public void save(InputStream stream) throws IOException {
        super.save(stream);
    }
}

