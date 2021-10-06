package com.epam.songmanager.model.entity.builder;

import com.epam.songmanager.model.resource.ResourceObj;
import com.epam.songmanager.model.resource.decoration.UnzipResource;

public class ResourceBuilderImpl implements ResourceBuilder{

    private ResourceObj resourceObj;

    public ResourceBuilderImpl(ResourceObj resourceObj) {
        this.resourceObj = resourceObj;
    }

    @Override
    public ResourceBuilder withUnzip() {
            resourceObj = new UnzipResource(resourceObj);
        return this;
    }

    @Override
    public ResourceBuilder withCompression() {
        return this;
    }

    @Override
    public ResourceObj build() {
        return resourceObj;
    }
}
