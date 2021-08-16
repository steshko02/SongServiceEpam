package com.epam.songmanager.model.entity;

import com.epam.songmanager.model.file_entity.CloudStorageEntity;
import com.epam.songmanager.model.file_entity.FileStorageEntity;

public enum StorageType {
    DISK_FILE_SYSTEM(FileStorageEntity.class.getName()),
    CLOUD_SYSTEM(CloudStorageEntity.class.getName());

    private final String type;

    StorageType(String type) {
        this.type = type;
    }

    public static StorageType getTypeByClass(String type) {
        if(type.equals(CloudStorageEntity.class.getName()))
            return CLOUD_SYSTEM;
        else if (type.equals(FileStorageEntity.class.getName()))
            return DISK_FILE_SYSTEM;
        else  return null;
    }
}
