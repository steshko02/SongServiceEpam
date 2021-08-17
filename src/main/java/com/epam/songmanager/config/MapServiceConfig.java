package com.epam.songmanager.config;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.file_entity.CloudStorageEntity;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class MapServiceConfig  {
//    @Autowired
//    private StorageService<CloudStorageEntity> minioService;
//    @Autowired
//    private StorageService<FileStorageEntity> fileSystemStorageService;

    @Bean("serviceHashMap")
        public HashMap<StorageType, StorageService<?>> mainCharactersByFilmName(
            StorageService<CloudStorageEntity> minioService,
            StorageService<FileStorageEntity> fileSystemStorageService) {

            HashMap<StorageType, StorageService<?>> result = new HashMap<>();

            result.put(StorageType.CLOUD_SYSTEM,  minioService);
            result.put(StorageType.DISK_FILE_SYSTEM, fileSystemStorageService);
            return result;
        }
}
