package com.epam.songmanager.service.converters;

import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.storage.FSStorage;
import com.epam.songmanager.model.storage.MinioStorage;
import com.epam.songmanager.model.storage.Storage;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;


public class MapJsonToStorage implements Converter<DBObject, Storage> {
    @Override
    public Storage convert(DBObject source) {
        StorageType storageType = (StorageType) source.get("storageType");
        switch (storageType) {
            case CLOUD_SYSTEM:
                return new MinioStorage((String)source.get("_id"),(String) source.get("bucket"),storageType,
                        (String) source.get("URL"),(String) source.get("accessKey"),
                        (String) source.get("secretKey"));
            case DISK_FILE_SYSTEM:
                return new FSStorage((String)source.get("_id"),(String) source.get("path"),storageType);
            default:
                return null;
        }
    }
}
