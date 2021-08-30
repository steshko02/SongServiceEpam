package com.epam.songmanager.service.interfaces;

import com.epam.songmanager.facades.CreateResource;
import com.epam.songmanager.model.entity.StorageType;

public interface CreateFileSwitcher {
    CreateResource<?> getByType(StorageType storageType);
}
