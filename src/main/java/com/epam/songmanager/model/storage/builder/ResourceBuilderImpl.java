package com.epam.songmanager.model.storage.builder;

import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.model.resource.decoration.CompressionResource;

public class ResourceBuilderImpl implements ResourceBuilder{

    private ResourceObj resourceObj;

    public ResourceBuilderImpl(ResourceObj resourceObj) {
        this.resourceObj = resourceObj;
    }

    @Override
    public ResourceBuilder withCompression() {
        CompressionResource compressionResource = new CompressionResource(resourceObj);
        this.resourceObj = compressionResource;
        return  this;
    }

    @Override
    public ResourceObj build() {
        return resourceObj;
    }
}
