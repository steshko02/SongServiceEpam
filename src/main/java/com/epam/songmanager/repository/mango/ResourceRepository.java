package com.epam.songmanager.repository.mango;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceObj;

public interface ResourceRepository {
    ResourceObj getResource(StorageType storageType );
    ResourceObj saveResource(ResourceObj resourceObj);
    ResourceObj getResourceById(String id);
}
