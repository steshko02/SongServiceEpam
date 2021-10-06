package com.epam.songmanager.repository.mango;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.storage.Storage;

public interface StorageRepository {

    Storage getStorage(StorageType storageType);
    void  saveStorage(Storage storage);
    Storage getStorageById(String id);
}
