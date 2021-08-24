package com.epam.songmanager.service.interfaces;

import com.epam.songmanager.model.entity.StorageType;

public interface StorageSwitcher {
    StorageService<?> getByType(StorageType storageType);
}
