package com.epam.songmanager.model.storage;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.storage.builder.ResourceBuilder;
import com.epam.songmanager.model.resource.ResourceObj;

public interface Storage {
    StorageType getType();
    ResourceObj createNewResource();
    String getId();
    ResourceBuilder requestBuilder();

}
