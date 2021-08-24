package com.epam.songmanager.model.resource;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public abstract class ResourceDecorator  implements ResourceObj {

    private ResourceObj delegate;

    private  String path;

    private  long size;

    private  String  checkSum;
    public ResourceDecorator(ResourceObj delegate) {
        this.delegate = delegate;
    }

    public ResourceDecorator() {

    }

    public ResourceDecorator( String path, long size,String checkSum) {
        this.checkSum = checkSum;
        this.path = path;
        this.size = size;
    }


    @Override
    public InputStream read() throws IOException {
        return delegate.read();
    }
}
