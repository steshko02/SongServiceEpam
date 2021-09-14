package com.epam.songmanager.repository.converter;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.CloudStorageEntity;
import com.epam.songmanager.model.resource.FileStorageEntity;
import com.epam.songmanager.model.resource.ResourceObj;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class MapJsonToResourceObj implements Converter<DBObject, ResourceObj> {

    @Override
    public ResourceObj convert(DBObject source) {
        StorageType storageType = (StorageType) source.get("storageType");
        switch (storageType){
            case CLOUD_SYSTEM: return new CloudStorageEntity(StorageType.CLOUD_SYSTEM);
            case DISK_FILE_SYSTEM: return new FileStorageEntity(StorageType.DISK_FILE_SYSTEM);
            default: return null;
        }
    }
}