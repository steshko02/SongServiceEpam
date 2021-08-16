package com.epam.songmanager.config.bean_config;

import com.epam.songmanager.facades.CreateSongsAndResource;
import com.epam.songmanager.model.file_entity.CloudStorageEntity;
import com.epam.songmanager.model.file_entity.FileStorageEntity;
import com.epam.songmanager.service.impl.FileSystemStorageService;
import com.epam.songmanager.service.impl.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateSongsAndResourceConfig {

    @Autowired
    private MinioService minioService;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @Bean
    public CreateSongsAndResource<FileStorageEntity> getFileObjBean(){
        return new CreateSongsAndResource<>(fileSystemStorageService);
    }

    @Bean
    public CreateSongsAndResource<CloudStorageEntity> getCloudObjBean(){
        return new CreateSongsAndResource<>(minioService);
    }

    public CreateSongsAndResourceConfig() {
    }
}
