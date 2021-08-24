package com.epam.songmanager.config.service;

import com.epam.songmanager.config.properties.FileProperties;
import com.epam.songmanager.config.properties.MessageDigestProperties;
import com.epam.songmanager.facades.CreateSongsAndResource;
import com.epam.songmanager.model.resource.CloudStorageEntity;
import com.epam.songmanager.model.resource.FileStorageEntity;
import com.epam.songmanager.service.impl.FileSystemStorageService;
import com.epam.songmanager.service.impl.MinioService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ MessageDigestProperties.class})
public class CreateSongsAndResourceConfig {

    @Bean
    public CreateSongsAndResource<FileStorageEntity> getFileObjBean(
            FileSystemStorageService fileSystemStorageService,
            MessageDigestProperties messageDigestProperties
    ){
        return new CreateSongsAndResource<>(fileSystemStorageService,
                messageDigestProperties.getMessageDigestType());
    }

    @Bean
    public CreateSongsAndResource<CloudStorageEntity> getCloudObjBean(
            MinioService minioService, MessageDigestProperties messageDigestProperties
    ){
        return new CreateSongsAndResource<>(minioService,
                messageDigestProperties.getMessageDigestType());
    }

}
