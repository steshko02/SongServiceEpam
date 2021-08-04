package com.epam.songmanager.model.file_entity;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

@Data
public abstract class ResourceDecorator  implements ResourceObj {

    private ResourceObj delegate;

    private  String  checkSum;

    private  String path;

    private  long size;


    public ResourceDecorator(ResourceObj delegate) {
        this.delegate = delegate;
    }

    public ResourceDecorator() {

    }

    public ResourceDecorator(String checkSum, String path, long size) {
        this.checkSum = checkSum;
        this.path = path;
        this.size = size;
    }

    @Override
    public void save(InputStream is) throws IOException, NoSuchAlgorithmException {
        delegate.save(is);
    }

    @Override
    public InputStream read() throws IOException {
        return delegate.read();
    }
}
