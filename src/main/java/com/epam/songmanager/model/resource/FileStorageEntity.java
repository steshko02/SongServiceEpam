package com.epam.songmanager.model.resource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter

public class FileStorageEntity extends ResourceDecorator {


        public FileStorageEntity(String checkSumRes, String path, long length) {
        super(checkSumRes,path,length);
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
