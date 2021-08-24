package com.epam.songmanager.config;
import com.epam.songmanager.model.entity.StorageType;
import com.epam.songmanager.model.resource.CloudStorageEntity;
import com.epam.songmanager.model.resource.FileStorageEntity;
import com.epam.songmanager.service.interfaces.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class MapServiceConfig  {

    @Bean("serviceHashMap")
        public HashMap<StorageType, StorageService<?>> getServiceMap(
            StorageService<CloudStorageEntity> minioService,
            StorageService<FileStorageEntity> fileSystemStorageService) {

            HashMap<StorageType, StorageService<?>> result = new HashMap<>();

            result.put(StorageType.CLOUD_SYSTEM,  minioService);
            result.put(StorageType.DISK_FILE_SYSTEM, fileSystemStorageService);
            return result;
        }
}
