package com.epam.songmanager.model.resource;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public abstract class ResourceDecorator implements ResourceObj  {

    private final ResourceObj delegate;

    public InputStream read(){
        return delegate.read();
    }

    public ResourceDecorator(ResourceObj delegate) {
        this.delegate = delegate;
    }
}
