package com.epam.songmanager.model.resource;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

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
    public InputStream read() throws IOException {
        return delegate.read();
    }
}
