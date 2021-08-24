package com.epam.songmanager.service.interfaces;


import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.ResourceDecorator;

public interface StorageSwitcher {
    StorageService<?> getByType(StorageType storageType);
}
